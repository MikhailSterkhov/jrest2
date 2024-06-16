package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.io.InputStream;

/**
 * Интерфейс для декодировщика HTTP сообщений.
 */
public interface HttpDecoder extends StreamCodec {

    /**
     * Декодирует HTTP запрос из входного потока.
     *
     * @param inputStream входной поток, содержащий данные HTTP запроса
     * @return декодированный HTTP запрос
     */
    HttpRequest decode0(InputStream inputStream);

    /**
     * Декодирует HTTP ответ из входного потока.
     *
     * @param inputStream входной поток, содержащий данные HTTP ответа
     * @return декодированный HTTP ответ
     */
    HttpResponse decode1(InputStream inputStream);
}
