package com.jrest.http.server;

import com.jrest.http.api.HttpListener;
import com.jrest.http.api.socket.HttpServerSocket;
import com.jrest.http.api.socket.HttpSocket;
import com.jrest.http.api.socket.HttpSocketInput;
import com.jrest.http.server.repository.HttpRepositoryHandler;
import com.jrest.http.server.repository.HttpServerRepositoryException;
import com.jrest.http.server.repository.HttpServerRepositoryValidator;
import com.jrest.http.server.resource.HttpServerResources;
import lombok.Builder;
import lombok.ToString;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Builder
@ToString
public class HttpServer {

    private final InetSocketAddress socketAddress;
    private final ExecutorService executorService;

    private final HttpServerResources resources = HttpServerResources.create();

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

    public void registerListener(HttpListener listener) {
        // todo
    }

    public void registerAsyncListener(HttpListener listener) {
        // todo
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
        // todo
    }

    private void doBind(HttpServerSocket httpServerSocket) {
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

        // todo
    }
}
