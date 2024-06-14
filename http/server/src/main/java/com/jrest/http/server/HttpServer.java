package com.jrest.http.server;

import com.jrest.http.api.HttpListener;
import com.jrest.http.api.socket.HttpServerSocket;
import com.jrest.http.api.socket.HttpSocket;
import com.jrest.http.api.socket.HttpSocketInput;
import com.jrest.http.api.socket.HttpSocketOutput;
import com.jrest.http.server.repository.HttpRepositoryHandler;
import com.jrest.http.server.repository.HttpServerRepositoryException;
import com.jrest.http.server.repository.HttpServerRepositoryValidator;
import com.jrest.http.server.resource.HttpResourcePath;
import com.jrest.http.server.resource.HttpResourceUnit;
import com.jrest.http.server.resource.HttpServerResources;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import lombok.Builder;
import lombok.ToString;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

@Builder
@ToString
public class HttpServer {

    private final InetSocketAddress socketAddress;
    private final ExecutorService executorService;

    private final HttpServerResources resources = HttpServerResources.create();

    private final List<HttpListener> asyncListeners = new CopyOnWriteArrayList<>();

    private Charset readCharset;

    public void bind() {
        try {
            HttpServerSocket httpServerSocket = HttpServerSocket.builder()
                    .address(socketAddress)
                    .executorService(executorService)
                    .build();

            doBind(httpServerSocket);
        } catch (IOException exception) {
            throw new HttpServerException("bind", exception);
        }
    }

    public void registerListener(String uri, HttpListener listener) {
        resources.register(
                HttpResourceUnit.builder()
                        .path(HttpResourcePath.fromUri(uri))
                        .listener(listener)
                        .build());
    }

    public void registerAsyncListener(String uri, HttpListener listener) {
        registerListener(uri, listener);
        asyncListeners.add(listener);
    }

    public void registerListener(HttpListener listener) {
        registerListener("/", listener);
    }

    public void registerAsyncListener(HttpListener listener) {
        registerAsyncListener("/", listener);
    }

    public void registerRepository(Object repository) {
        HttpServerRepositoryValidator validator = HttpServerRepositoryValidator.fromRepository(repository);
        if (!validator.isHttpServer()) {
            throw new HttpServerRepositoryException("Repository " + repository.getClass() + " is not annotated as @HttpServer");
        }

        List<HttpRepositoryHandler> repositoryHandlers = validator.findRepositoryHandlers();

        for (HttpRepositoryHandler repositoryHandler : repositoryHandlers) {
            doRegisterAsListener(repositoryHandler);
        }
    }

    private void doRegisterAsListener(HttpRepositoryHandler repositoryHandler) {
        HttpListener listener = (request) -> {

            if (repositoryHandler.canProcess(request)) {
                return repositoryHandler.getInvocation().process(request);
            }

            return HttpResponse.SKIP_ACTION;
        };

        if (repositoryHandler.isAsynchronous()) {
            registerAsyncListener(listener);
        } else {
            registerListener(listener);
        }
    }

    private void doBind(HttpServerSocket httpServerSocket) {
        if (readCharset == null) {
            readCharset = Charset.defaultCharset();
        }

        CompletableFuture<HttpSocket> httpSocketCompletableFuture = httpServerSocket.awaitNewCompletion();
        httpSocketCompletableFuture.whenComplete((httpSocket, throwable) -> {

            if (throwable != null) {
                throw new HttpServerException("http-request acceptation", throwable);
            }

            onAccepted(httpSocket);
            doBind(httpServerSocket);
        });
    }

    private void onAccepted(HttpSocket httpSocket) {
        HttpSocketInput httpSocketInput = httpSocket.read();

        HttpRequest httpRequest = httpSocketInput.toHttpRequest(readCharset);
        Optional<HttpResponse> httpResponseOptional = processHttpRequest(httpRequest);

        httpResponseOptional.ifPresent(httpResponse ->
                httpSocket.write(new HttpSocketOutput().write(httpResponse)));
    }

    private Optional<HttpResponse> processHttpRequest(HttpRequest httpRequest) {
        Set<HttpResourceUnit> allResourcesUnits = resources.getAllResourcesUnits();
        List<HttpResponse> responsesList = new ArrayList<>();

        for (HttpResourceUnit httpResourceUnit : allResourcesUnits) {
            HttpResponse httpResponse = processHttpListener(httpRequest,
                    httpResourceUnit.getListener())
                    .join();

            if (httpResponse != HttpResponse.SKIP_ACTION) {
                responsesList.add(httpResponse);
            }
        }

        if (responsesList.size() > 1) {
            throw new HttpServerException("Http request " + httpRequest.getMethod().getName() + " " + httpRequest.getUri() + " was proceed more then 1 responses");
        }

        return responsesList.stream().findFirst();
    }

    private CompletableFuture<HttpResponse> processHttpListener(HttpRequest httpRequest, HttpListener listener) {
        if (asyncListeners.contains(listener)) {
            return CompletableFuture.supplyAsync(() -> listener.process(httpRequest),
                    executorService);
        }
        return CompletableFuture.completedFuture(listener.process(httpRequest));
    }
}
