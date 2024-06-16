package com.jrest.http.client;

import com.jrest.mvc.model.HttpProtocol;
import lombok.experimental.UtilityClass;

import java.util.concurrent.ExecutorService;

/**
 * Утилитный класс для создания HTTP клиентов.
 */
@UtilityClass
public class HttpClients {

    /**
     * Создает HTTP клиент на основе сокетов с указанным исполнителем.
     *
     * @param executorService исполнитель для клиента
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(ExecutorService executorService) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .executorService(executorService)
                .build();
    }

    /**
     * Создает HTTP клиент на основе сокетов с указанным исполнителем и настройкой keep-alive.
     *
     * @param executorService исполнитель для клиента
     * @param keepAlive       флаг keep-alive
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(ExecutorService executorService, boolean keepAlive) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .executorService(executorService)
                .keepAlive(keepAlive)
                .build();
    }

    /**
     * Создает HTTP клиент на основе сокетов с указанным исполнителем и таймаутом соединения.
     *
     * @param executorService исполнитель для клиента
     * @param connectTimeout таймаут соединения в миллисекундах
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(ExecutorService executorService, int connectTimeout) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .executorService(executorService)
                .connectTimeout(connectTimeout)
                .build();
    }

    /**
     * Создает HTTP клиент на основе сокетов с указанным исполнителем, таймаутом соединения и настройкой keep-alive.
     *
     * @param executorService исполнитель для клиента
     * @param connectTimeout таймаут соединения в миллисекундах
     * @param keepAlive       флаг keep-alive
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(ExecutorService executorService, int connectTimeout, boolean keepAlive) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .executorService(executorService)
                .connectTimeout(connectTimeout)
                .keepAlive(keepAlive)
                .build();
    }

    /**
     * Создает HTTP клиент на основе сокетов без указанного исполнителя.
     *
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient() {
        return createSocketClient(null);
    }

    /**
     * Создает HTTP клиент на основе сокетов с настройкой keep-alive.
     *
     * @param keepAlive флаг keep-alive
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(boolean keepAlive) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .keepAlive(keepAlive)
                .build();
    }

    /**
     * Создает HTTP клиент на основе сокетов с указанным таймаутом соединения.
     *
     * @param connectTimeout таймаут соединения в миллисекундах
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(int connectTimeout) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .connectTimeout(connectTimeout)
                .build();
    }

    /**
     * Создает HTTP клиент на основе сокетов с указанным таймаутом соединения и настройкой keep-alive.
     *
     * @param connectTimeout таймаут соединения в миллисекундах
     * @param keepAlive       флаг keep-alive
     * @return экземпляр HTTP клиента
     */
    public HttpClient createSocketClient(int connectTimeout, boolean keepAlive) {
        return SocketHttpClient.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .connectTimeout(connectTimeout)
                .keepAlive(keepAlive)
                .build();
    }

    /**
     * Создает HTTP клиент на основе URL соединения с указанным исполнителем.
     *
     * @param executorService исполнитель для клиента
     * @return экземпляр HTTP клиента
     */
    public HttpClient createClient(ExecutorService executorService) {
        return URLHttpClient.builder()
                .executorService(executorService)
                .build();
    }

    /**
     * Создает HTTP клиент на основе URL соединения без указанного исполнителя.
     *
     * @return экземпляр HTTP клиента
     */
    public HttpClient createClient() {
        return createClient(null);
    }
}
