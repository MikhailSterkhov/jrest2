package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.io.ByteArrayOutputStream;

/**
 * Интерфейс для кодировщика HTTP сообщений.
 */
public interface HttpEncoder extends StreamCodec {

    /**
     * Кодирует HTTP запрос в массив байтов.
     *
     * @param httpRequest HTTP запрос для кодирования
     * @return массив байтов, содержащий закодированный HTTP запрос
     */
    ByteArrayOutputStream encode0(HttpRequest httpRequest);

    /**
     * Кодирует HTTP ответ в массив байтов.
     *
     * @param httpResponse HTTP ответ для кодирования
     * @return массив байтов, содержащий закодированный HTTP ответ
     */
    ByteArrayOutputStream encode1(HttpResponse httpResponse);
}
