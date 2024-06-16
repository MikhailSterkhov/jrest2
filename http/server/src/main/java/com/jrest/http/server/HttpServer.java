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
import com.jrest.mvc.model.*;
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
import java.util.function.Consumer;

/**
 * Класс для создания и управления HTTP-сервером.
 */
public class HttpServer {

    private final InetSocketAddress socketAddress;
    private final ExecutorService executorService;

    private final HttpServerResources resources = HttpServerResources.create();
    private final HttpServerResources beforeResources = HttpServerResources.create();

    private final List<HttpListener> asyncListeners = new CopyOnWriteArrayList<>();
    private final List<HttpListener> asyncBeforeListeners = new CopyOnWriteArrayList<>();

    private final HttpProtocol protocol;
    private HttpServerSocketChannel httpServerSocketChannel;

    private final SslContent sslContent;
    private final HttpListener notFoundListener;

    /**
     * Конструктор для создания экземпляра HTTP-сервера.
     *
     * @param socketAddress   Адрес и порт для биндинга сервера.
     * @param executorService Сервис для выполнения потоков, если не задан, используется кэшированный пул потоков.
     * @param protocol        Протокол HTTP, по умолчанию HTTP/1.1.
     * @param ssl             Настройки SSL для HTTPS, если null, используется HTTP.
     * @param notFoundListener Слушатель для обработки запросов, которые не нашли соответствующего обработчика.
     */
    @Builder
    public HttpServer(InetSocketAddress socketAddress, ExecutorService executorService, HttpProtocol protocol,
                      SslContent ssl, HttpListener notFoundListener) {
        this.socketAddress = socketAddress;
        this.sslContent = ssl;
        this.notFoundListener = notFoundListener;
        this.executorService = Optional.ofNullable(executorService).orElseGet(Executors::newCachedThreadPool);
        this.protocol = Optional.ofNullable(protocol).orElse(HttpProtocol.HTTP_1_1);
    }

    /**
     * Метод для биндинга сервера к указанному адресу и порту.
     * Настраивает и запускает канал сервера.
     */
    public void bind() {
        try {
            HttpServerSocketConfig config =
                    HttpServerSocketConfig.builder()
                            .protocol(HttpProtocol.HTTP_1_1)
                            .address(socketAddress)
                            .ssl(sslContent != null)
                            .keepAlive(true)
                            .keystorePath(sslContent != null ? sslContent.getKeystorePath() : null)
                            .keyPassword(sslContent != null ? sslContent.getKeyPassword() : null)
                            .keystorePassword(sslContent != null ? sslContent.getKeystorePassword() : null)
                            .build();

            httpServerSocketChannel = new HttpServerSocketChannel(config, executorService,
                    (clientChannel, request) -> {
                        if (request.getHeaders() == null) {
                            request.setHeaders(Headers.newHeaders());
                        }
                        if (request.getAttributes() == null) {
                            request.setAttributes(Attributes.newAttributes());
                        }

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

    /**
     * Метод для завершения работы сервера.
     */
    public void shutdown() {
        httpServerSocketChannel.shutdown();
    }

    /**
     * Регистрация синхронного слушателя, который выполняется перед основными обработчиками.
     *
     * @param uri      URI, на который будет реагировать слушатель.
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerBeforeListener(String uri, Consumer<HttpRequest> listener) {
        beforeResources.register(
                HttpResourceUnit.builder()
                        .path(HttpResourcePath.fromUri(uri))
                        .listener((httpRequest -> {
                            listener.accept(httpRequest);
                            return HttpListener.SKIP_ACTION;
                        }))
                        .build());
    }

    /**
     * Регистрация асинхронного слушателя, который выполняется перед основными обработчиками.
     *
     * @param uri      URI, на который будет реагировать слушатель.
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerAsyncBeforeListener(String uri, Consumer<HttpRequest> listener) {
        HttpListener httpListener = (httpRequest -> {
            listener.accept(httpRequest);
            return HttpListener.SKIP_ACTION;
        });
        beforeResources.register(
                HttpResourceUnit.builder()
                        .path(HttpResourcePath.fromUri(uri))
                        .listener(httpListener)
                        .build());
        asyncBeforeListeners.add(httpListener);
    }

    /**
     * Регистрация синхронного слушателя для обработки запросов.
     *
     * @param uri      URI, на который будет реагировать слушатель.
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerListener(String uri, HttpListener listener) {
        resources.register(
                HttpResourceUnit.builder()
                        .path(HttpResourcePath.fromUri(uri))
                        .listener(listener)
                        .build());
    }

    /**
     * Регистрация асинхронного слушателя для обработки запросов.
     *
     * @param uri      URI, на который будет реагировать слушатель.
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerAsyncListener(String uri, HttpListener listener) {
        registerListener(uri, listener);
        asyncListeners.add(listener);
    }

    /**
     * Регистрация синхронного слушателя, который выполняется перед основными обработчиками для всех URI.
     *
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerBeforeListener(Consumer<HttpRequest> listener) {
        registerBeforeListener("*", listener);
    }

    /**
     * Регистрация асинхронного слушателя, который выполняется перед основными обработчиками для всех URI.
     *
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerAsyncBeforeListener(Consumer<HttpRequest> listener) {
        registerAsyncBeforeListener("*", listener);
    }

    /**
     * Регистрация синхронного слушателя для обработки запросов для всех URI.
     *
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerListener(HttpListener listener) {
        registerListener("*", listener);
    }

    /**
     * Регистрация асинхронного слушателя для обработки запросов для всех URI.
     *
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerAsyncListener(HttpListener listener) {
        registerAsyncListener("*", listener);
    }

    /**
     * Регистрация репозитория с обработчиками.
     *
     * @param repository Репозиторий, содержащий методы, аннотированные для обработки HTTP-запросов.
     * @throws HttpServerRepositoryException если репозиторий не аннотирован как @HttpServer.
     */
    public void registerRepository(Object repository) {
        HttpServerRepositoryValidator validator = HttpServerRepositoryValidator.fromRepository(repository);
        if (!validator.isHttpServer()) {
            throw new HttpServerRepositoryException("Repository " + repository.getClass() + " is not annotated as @HttpServer");
        }

        for (HttpRepositoryHandler repositoryHandler : validator.findProcessingHandlers()) {
            registerHandler(repositoryHandler);
        }
        for (HttpRepositoryHandler repositoryHandler : validator.findBeforeHandlers()) {
            registerBeforeHandler(repositoryHandler);
        }
    }

    /**
     * Преобразование обработчика репозитория в слушателя HTTP.
     *
     * @param repositoryHandler Обработчик репозитория.
     * @return Слушатель HTTP.
     */
    private HttpListener toHttpListener(HttpRepositoryHandler repositoryHandler) {
        return (request) -> {
            if (repositoryHandler.canProcess(request)) {
                return repositoryHandler.getInvocation().process(request);
            }

            return HttpListener.SKIP_ACTION;
        };
    }

    /**
     * Регистрация обработчика репозитория.
     *
     * @param repositoryHandler Обработчик репозитория.
     */
    private void registerHandler(HttpRepositoryHandler repositoryHandler) {
        HttpListener httpListener = toHttpListener(repositoryHandler);
        String uri = repositoryHandler.getUri();

        if (repositoryHandler.isAsynchronous()) {
            registerAsyncListener(uri, httpListener);
        } else {
            registerListener(uri, httpListener);
        }
    }

    /**
     * Регистрация обработчика репозитория, выполняемого перед основными обработчиками.
     *
     * @param repositoryHandler Обработчик репозитория.
     */
    private void registerBeforeHandler(HttpRepositoryHandler repositoryHandler) {
        Consumer<HttpRequest> consumer = ((httpRequest) -> repositoryHandler.getInvocation().process(httpRequest));
        String uri = repositoryHandler.getUri();

        if (repositoryHandler.isAsynchronous()) {
            registerBeforeListener(uri, consumer);
        } else {
            registerAsyncBeforeListener(uri, consumer);
        }
    }

    /**
     * Обработка HTTP-запроса.
     *
     * @param httpRequest Запрос, который нужно обработать.
     * @return Опциональный HTTP-ответ.
     */
    private Optional<HttpResponse> processHttpRequest(HttpRequest httpRequest) {
        for (HttpResourceUnit beforeUnit : beforeResources.getAllResourcesUnits()) {
            if (!beforeUnit.isExpected("*") && !beforeUnit.isExpected(httpRequest.getUrl())) {
                continue;
            }

            HttpListener httpListener = beforeUnit.getListener();

            if (asyncBeforeListeners.contains(httpListener)) {
                CompletableFuture.runAsync(() -> httpListener.process(httpRequest),
                        executorService);
            } else {
                httpListener.process(httpRequest);
            }
        }

        List<HttpResponse> responsesList = toResponsesList(httpRequest, resources.getAllResourcesUnits());

        if (responsesList.size() > 1) {
            throw new HttpServerException("Http request " + httpRequest.getMethod() + " " + httpRequest.getPath() + " was proceed more then 1 responses");
        }

        return responsesList.stream().findFirst();
    }

    /**
     * Преобразование HTTP-запроса в список ответов.
     *
     * @param httpRequest Запрос, который нужно обработать.
     * @param resourceUnits Список ресурсов для обработки.
     * @return Список HTTP-ответов.
     */
    private List<HttpResponse> toResponsesList(HttpRequest httpRequest, List<HttpResourceUnit> resourceUnits) {
        List<HttpResponse> responsesList = new ArrayList<>();

        for (HttpResourceUnit httpResourceUnit : resourceUnits) {
            if (!httpResourceUnit.isExpected("*") && !httpResourceUnit.isExpected(httpRequest.getUrl())) {
                continue;
            }

            HttpResponse httpResponse = processHttpListener(httpRequest, httpResourceUnit.getListener()).join();

            if (httpResponse != HttpListener.SKIP_ACTION) {
                responsesList.add(httpResponse);
            }
        }

        return responsesList;
    }

    /**
     * Обработка слушателя HTTP-запросов.
     *
     * @param httpRequest Запрос, который нужно обработать.
     * @param listener    Слушатель HTTP.
     * @return CompletableFuture с HTTP-ответом.
     */
    private CompletableFuture<HttpResponse> processHttpListener(HttpRequest httpRequest, HttpListener listener) {
        if (asyncListeners.contains(listener)) {
            return CompletableFuture.supplyAsync(() -> listener.process(httpRequest),
                    executorService);
        }
        return CompletableFuture.completedFuture(listener.process(httpRequest));
    }
}
