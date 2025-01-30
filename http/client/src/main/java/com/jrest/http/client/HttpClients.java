package com.jrest.http.client;

import com.jrest.binary.*;
import com.jrest.http.client.impl.WrappedBinaryHttpClient;
import com.jrest.http.client.impl.SocketHttpClient;
import com.jrest.http.client.impl.DefaultHttpClient;
import com.jrest.mvc.model.HttpProtocol;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.file.Path;
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
        return DefaultHttpClient.builder()
                .executorService(executorService)
                .build();
    }

    /**
     * Создает HTTP клиент на основе URL соединения с указанным исполнителем.
     *
     * @param executorService исполнитель для клиента
     * @param connectTimeout  таймаут подключения к URL
     * @return экземпляр HTTP клиента
     */
    public HttpClient createClient(ExecutorService executorService, int connectTimeout) {
        return DefaultHttpClient.builder()
                .connectTimeout(connectTimeout)
                .executorService(executorService)
                .build();
    }

    /**
     * Создает HTTP клиент на основе URL соединения с указанным исполнителем.
     *
     * @param executorService исполнитель для клиента
     * @param connectTimeout  таймаут подключения к URL
     * @param readTimeout     таймаут чтения с потока соединения
     * @return экземпляр HTTP клиента
     */
    public HttpClient createClient(ExecutorService executorService, int connectTimeout, int readTimeout) {
        return DefaultHttpClient.builder()
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .executorService(executorService)
                .build();
    }

    /**
     * Создает HTTP клиент на основе URL соединения
     *
     * @param connectTimeout  таймаут подключения к URL
     * @return экземпляр HTTP клиента
     */
    public HttpClient createClient(int connectTimeout) {
        return DefaultHttpClient.builder()
                .connectTimeout(connectTimeout)
                .build();
    }

    /**
     * Создает HTTP клиент на основе URL соединения
     *
     * @param connectTimeout  таймаут подключения к URL
     * @param readTimeout     таймаут чтения с потока соединения
     * @return экземпляр HTTP клиента
     */
    public HttpClient createClient(int connectTimeout, int readTimeout) {
        return DefaultHttpClient.builder()
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
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

    /**
     * Создает {@link BinaryHttpClient} из {@link Reader}.
     *
     * @param httpClient клиент, который будет использоваться для выполнения запросов
     * @param reader     источник данных для бинарного HTTP клиента
     * @return экземпляр {@link BinaryHttpClient}
     */
    public BinaryHttpClient binary(HttpClient httpClient, Reader reader) {
        return WrappedBinaryHttpClient.builder()
                .httpClient(httpClient)
                .binary(HttpBinaryReader.read(reader))
                .build();
    }

    /**
     * Создает {@link BinaryHttpClient} из {@link InputStream}.
     *
     * @param httpClient  клиент, который будет использоваться для выполнения запросов
     * @param inputStream источник данных для бинарного HTTP клиента
     * @return экземпляр {@link BinaryHttpClient}
     */
    public BinaryHttpClient binary(HttpClient httpClient, InputStream inputStream) {
        return WrappedBinaryHttpClient.builder()
                .httpClient(httpClient)
                .binary(HttpBinaryReader.read(inputStream))
                .build();
    }

    /**
     * Создает {@link BinaryHttpClient} из {@link File}.
     *
     * @param httpClient клиент, который будет использоваться для выполнения запросов
     * @param file       файл, содержащий данные для бинарного HTTP клиента
     * @return экземпляр {@link BinaryHttpClient}
     */
    public BinaryHttpClient binary(HttpClient httpClient, File file) throws IOException {
        return WrappedBinaryHttpClient.builder()
                .httpClient(httpClient)
                .binary(HttpBinaryReader.read(file))
                .build();
    }

    /**
     * Создает {@link BinaryHttpClient} из {@link Path}.
     *
     * @param httpClient клиент, который будет использоваться для выполнения запросов
     * @param path       путь к файлу, содержащему данные для бинарного HTTP клиента
     * @return экземпляр {@link BinaryHttpClient}
     */
    public BinaryHttpClient binary(HttpClient httpClient, Path path) throws IOException {
        return WrappedBinaryHttpClient.builder()
                .httpClient(httpClient)
                .binary(HttpBinaryReader.read(path))
                .build();
    }
}
