package com.jrest.http.server;

import com.jrest.http.api.HttpListener;
import com.jrest.http.api.socket.HttpServerSocketChannel;
import com.jrest.http.api.socket.HttpServerSocketConfig;
import com.jrest.http.server.authentication.AuthorizationController;
import com.jrest.http.server.authentication.AuthenticatorContainer;
import com.jrest.http.server.authentication.AuthenticationTypes;
import com.jrest.http.server.repository.HttpAuthorizationHandler;
import com.jrest.http.server.repository.HttpRequestHandler;
import com.jrest.http.server.repository.HttpRepositoryHelper;
import com.jrest.http.server.resource.HttpResourcePath;
import com.jrest.http.server.resource.HttpResourceUnit;
import com.jrest.http.server.resource.HttpServerResources;
import com.jrest.mvc.model.*;
import com.jrest.mvc.model.authentication.ApprovalResult;
import com.jrest.mvc.model.authentication.Authentication;
import com.jrest.mvc.model.authentication.HttpAuthenticator;
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

    private final AuthorizationController authorizationController = new AuthorizationController();
    private final AuthenticatorContainer authenticatorContainer = new AuthenticatorContainer();

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

    public void addAuthenticator(Authentication authentication, HttpAuthenticator authenticator) {
        authenticatorContainer.add(authentication, authenticator);
    }

    public void setAuthenticator(Authentication authentication, HttpAuthenticator authenticator) {
        authenticatorContainer.set(authentication, authenticator);
    }

    public void addAuthenticator(HttpAuthenticator authenticator) {
        for (Authentication authentication : AuthenticationTypes.values()) {
            addAuthenticator(authentication, authenticator);
        }
    }

    public void setAuthenticator(HttpAuthenticator authenticator) {
        for (Authentication authentication : AuthenticationTypes.values()) {
            setAuthenticator(authentication, authenticator);
        }
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
                        .listener(wrapHttpListenerWithAuthorization(
                                httpRequest -> { listener.accept(httpRequest); return HttpListener.SKIP_ACTION; }))
                        .build());
    }

    /**
     * Регистрация асинхронного слушателя, который выполняется перед основными обработчиками.
     *
     * @param uri      URI, на который будет реагировать слушатель.
     * @param listener Слушатель, который будет обрабатывать запрос.
     */
    public void registerAsyncBeforeListener(String uri, Consumer<HttpRequest> listener) {
        HttpListener httpListener = (httpRequest) -> { listener.accept(httpRequest); return HttpListener.SKIP_ACTION; };
        beforeResources.register(
                HttpResourceUnit.builder()
                        .path(HttpResourcePath.fromUri(uri))
                        .listener(wrapHttpListenerWithAuthorization(httpListener))
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
                        .listener(wrapHttpListenerWithAuthorization(listener))
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
     * @throws HttpServerException если репозиторий не аннотирован как @HttpServer.
     */
    public void registerRepository(Object repository) {
        HttpRepositoryHelper repositoryHelper = HttpRepositoryHelper.fromRepository(repository);
        if (!repositoryHelper.isHttpServer()) {
            throw new HttpServerException("Repository " + repository.getClass() + " is not annotated as @HttpServer");
        }

        List<HttpAuthorizationHandler> authorizationHandlers = repositoryHelper.findAuthorizationHandlers();

        List<HttpRequestHandler> processingHandlers = repositoryHelper.findProcessingHandlers();
        List<HttpRequestHandler> beforeHandlers = repositoryHelper.findBeforeHandlers();

        for (Authentication authentication : AuthenticationTypes.values()) {
            for (HttpAuthorizationHandler authorizationHandler : authorizationHandlers) {
                addAuthenticator(authentication, wrapAuthenticatorWithAsync(authorizationHandler.isAsynchronous(),
                        authorizationHandler.getAuthenticator()));
            }
        }
        for (HttpRequestHandler repositoryHandler : processingHandlers) {
            registerHandler(repositoryHandler);
        }
        for (HttpRequestHandler repositoryHandler : beforeHandlers) {
            registerBeforeHandler(repositoryHandler);
        }
    }

    private HttpAuthenticator wrapAuthenticatorWithAsync(boolean isAsync, HttpAuthenticator httpAuthenticator) {
        HttpAuthenticator authenticator = httpAuthenticator;
        if (isAsync) {
            authenticator = (unapprovedRequest) ->
                    CompletableFuture.supplyAsync(() ->
                            httpAuthenticator.authenticate(unapprovedRequest), executorService).join();
        }
        return authenticator;
    }

    private HttpListener wrapHttpListenerWithAuthorization(HttpListener httpListener) {
        return (httpRequest) -> {
            ApprovalResult result = authorize(httpRequest);
            if (result.isForbidden()) {
                return result.getForbiddenResponse();
            }
            return httpListener.process(httpRequest);
        };
    }

    private ApprovalResult authorize(HttpRequest httpRequest) {
        if (!authorizationController.hasAuthentication(httpRequest)) {
            int authenticatorsCount = authenticatorContainer.getAllAuthenticators().size();
            if (authenticatorsCount > 0) {
                return ApprovalResult.forbidden();
            } else {
                return ApprovalResult.skip();
            }
        }
        Authentication authentication = authorizationController.findAuthentication(httpRequest);
        return authorizationController.authenticate(httpRequest, authentication,
                authenticatorContainer.getAuthenticators(authentication));
    }

    /**
     * Преобразование обработчика репозитория в слушателя HTTP.
     *
     * @param requestHandler Обработчик репозитория.
     * @return Слушатель HTTP.
     */
    private HttpListener toHttpListener(HttpRequestHandler requestHandler) {
        return (request) -> {
            if (requestHandler.canProcess(request)) {
                if (!requestHandler.isNotAuthorized()) {

                    ApprovalResult result = authorize(request);
                    if (result.isForbidden()) {
                        return result.getForbiddenResponse();
                    }
                }
                HttpListener httpListener = requestHandler.getInvocation();
                return httpListener.process(request);
            }

            return HttpListener.SKIP_ACTION;
        };
    }

    /**
     * Регистрация обработчика репозитория.
     *
     * @param repositoryHandler Обработчик репозитория.
     */
    private void registerHandler(HttpRequestHandler repositoryHandler) {
        HttpListener httpListener = toHttpListener(repositoryHandler);
        String uri = repositoryHandler.getUri();

        resources.register(
                HttpResourceUnit.builder()
                        .path(HttpResourcePath.fromUri(uri))
                        .listener(httpListener)
                        .build());

        if (repositoryHandler.isAsynchronous()) {
            asyncListeners.add(httpListener);
        }
    }

    /**
     * Регистрация обработчика репозитория, выполняемого перед основными обработчиками.
     *
     * @param repositoryHandler Обработчик репозитория.
     */
    private void registerBeforeHandler(HttpRequestHandler repositoryHandler) {
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
