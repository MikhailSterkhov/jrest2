package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.HttpProtocol;

public interface StreamCodec {

    String SWAP_DELIMITER = "\r\n";

    HttpProtocol protocol();
}
