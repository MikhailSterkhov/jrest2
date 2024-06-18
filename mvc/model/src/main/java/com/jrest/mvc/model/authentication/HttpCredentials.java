package com.jrest.mvc.model.authentication;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.UnaryOperator;

/**
 * Представляет учетные данные HTTP с
 * возможностью кодирования и декодирования.
 */
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpCredentials {

    private final AuthenticationHash hash;

    private final Token token;

    private final boolean encoded;
    private final boolean decoded;

    /**
     * Кодирует учетные данные с использованием заданного оператора кодирования.
     *
     * @return Новый экземпляр HttpCredentials с закодированным токеном.
     *         Если учетные данные уже закодированы или оператор кодирования не задан,
     *         возвращается текущий экземпляр HttpCredentials.
     */
    public HttpCredentials tryEncode() {
        if (isEncoded()) {
            return this;
        }
        UnaryOperator<String> encodeOperator = hash.getEncodeOperator();
        if (encodeOperator == null) {
            return this;
        }
        String token = encodeOperator.apply(this.token.getValue());
        return toBuilder()
                .token(Token.of(token))
                .encoded(true)
                .decoded(false).build();
    }

    /**
     * Декодирует учетные данные с использованием заданного оператора декодирования.
     *
     * @return Новый экземпляр HttpCredentials с декодированным токеном.
     *         Если учетные данные уже декодированы или оператор декодирования не задан,
     *         возвращается текущий экземпляр HttpCredentials.
     */
    public HttpCredentials tryDecode() {
        if (isDecoded()) {
            return this;
        }
        UnaryOperator<String> decodeOperator = hash.getDecodeOperator();
        if (decodeOperator == null) {
            return this;
        }
        String token = decodeOperator.apply(this.token.getValue());
        return toBuilder()
                .token(Token.of(token))
                .encoded(false)
                .decoded(true).build();
    }
}
