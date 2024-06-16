package com.jrest.http.server;

import com.jrest.http.api.HttpListener;
import com.jrest.http.api.socket.HttpServerSocketChannel;
import com.jrest.http.api.socket.HttpServerSocketConfig;
import com.jrest.http.server.repository.HttpRepositoryHandler;
import com.jrest.http.server.repository.HttpServerRepositoryException;
import com.jrest.http.server.repository.HttpServerRepositoryValidator;
import com.jrest.http.server.resource.HttpResourcePath;
import com.jrest.http.server.resource.HttpResourceUnit;
import com.jrest.http.server.resource.HttpServerResources;
import com.jrest.mvc.model.HttpProtocol;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.ResponseCode;
import lombok.Builder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private final InetSocketAddress socketAddress;
    private final ExecutorService executorService;

    private final HttpServerResources resources = HttpServerResources.create();

    private final List<HttpListener> asyncListeners = new CopyOnWriteArrayList<>();

    private final HttpProtocol protocol;
    private HttpServerSocketChannel httpServerSocketChannel;

    private final SslContent sslContent;
    private final HttpListener notFoundListener;

    @Builder
    public HttpServer(InetSocketAddress socketAddress, ExecutorService executorService, HttpProtocol protocol,
                      SslContent ssl, HttpListener notFoundListener) {
        this.socketAddress = socketAddress;
        this.sslContent = ssl;
        this.notFoundListener = notFoundListener;
        this.executorService = Optional.ofNullable(executorService).orElseGet(Executors::newCachedThreadPool);
        this.protocol = Optional.ofNullable(protocol).orElse(HttpProtocol.HTTP_1_1);
    }

    public void bind() {
        try {
            HttpServerSocketConfig config =
                    HttpServerSocketConfig.builder()
                            .protocol(HttpProtocol.HTTP_1_1)
                            .port(socketAddress.getPort())
                            .ssl(sslContent != null)
                            .keepAlive(true)
                            .keystorePath(sslContent != null ? sslContent.getKeystorePath() : null)
                            .keyPassword(sslContent != null ? sslContent.getKeyPassword() : null)
                            .keystorePassword(sslContent != null ? sslContent.getKeystorePassword() : null)
                            .build();

            httpServerSocketChannel = new HttpServerSocketChannel(config, executorService,
                    (clientChannel, request) -> {
                        Optional<HttpResponse> httpResponseOptional = processHttpRequest(request)
                                .map(httpResponse -> httpResponse.toBuilder().protocol(protocol).build());

                        try {
                            if (httpResponseOptional.isPresent()) {
                                clientChannel.sendResponse(
                                        httpResponseOptional.get());
                            } else {
                                HttpResponse httpResponse;
                                if (notFoundListener != null) {
                                    httpResponse = notFoundListener.process(request);
                                } else {
                                    httpResponse = HttpResponse.builder()
                                            .protocol(protocol)
                                            .code(ResponseCode.NOT_FOUND)
                                            .build();
                                }
                                clientChannel.sendResponse(httpResponse);
                            }
                        } catch (IOException exception) {
                            throw new HttpServerException(exception);
                        }
                    });

            httpServerSocketChannel.start();
        } catch (IOException exception) {
            throw new HttpServerException("bind", exception);
        }
    }

    public void shutdown() {
        httpServerSocketChannel.shutdown();
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
        registerListener("*", listener);
    }

    public void registerAsyncListener(HttpListener listener) {
        registerAsyncListener("*", listener);
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

            return HttpListener.SKIP_ACTION;
        };

        String uri = repositoryHandler.getUri();
        if (repositoryHandler.isAsynchronous()) {
            registerAsyncListener(uri, listener);
        } else {
            registerListener(uri, listener);
        }
    }

    private Optional<HttpResponse> processHttpRequest(HttpRequest httpRequest) {
        List<HttpResourceUnit> allResourcesUnits = resources.getAllResourcesUnits();
        List<HttpResponse> responsesList = new ArrayList<>();

        for (HttpResourceUnit httpResourceUnit : allResourcesUnits) {
            if (!httpResourceUnit.isExpected("*") && !httpResourceUnit.isExpected(httpRequest.getUrl())) {
                continue;
            }

            HttpResponse httpResponse = processHttpListener(httpRequest,
                    httpResourceUnit.getListener()).join();

            if (httpResponse != HttpListener.SKIP_ACTION) {
                responsesList.add(httpResponse);
            }
        }

        if (responsesList.size() > 1) {
            throw new HttpServerException("Http request " + httpRequest.getMethod().getName() + " " + httpRequest.getPath() + " was proceed more then 1 responses");
        }

        return responsesList.stream().findFirst();
    }

    private CompletableFuture<HttpResponse> processHttpListener(HttpRequest httpRequest, HttpListener listener) {
        if (asyncListeners.contains(listener)) {
            return CompletableFuture.supplyAsync(() -> listener.process(httpRequest));
        }
        HttpResponse process = listener.process(httpRequest);
        return CompletableFuture.completedFuture(process);
    }
}
