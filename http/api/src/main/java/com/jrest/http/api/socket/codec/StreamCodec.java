package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.HttpProtocol;

/**
 * Интерфейс для кодека потока данных.
 */
public interface StreamCodec {

    /**
     * Возвращает протокол, с которым работает данный кодек.
     *
     * @return протокол HTTP
     */
    HttpProtocol protocol();
}
