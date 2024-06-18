package com.jrest.mvc.model.authentication.defaults;

import com.jrest.mvc.model.authentication.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Реализация аутентификации HTTP Bearer.
 * <p>
 * Этот класс реализует аутентификацию HTTP Bearer, проверяя токен доступа.
 * Он сравнивает предоставленный токен с заранее определенным списком допустимых токенов
 * и возвращает результат аутентификации.
 * </p>
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpBearerAuthenticator implements HttpAuthenticator {

    /**
     * Создает новый экземпляр {@code HttpBearerAuthenticator} с предоставленным списком допустимых токенов.
     *
     * @param acceptableTokens список допустимых токенов.
     * @return новый экземпляр {@code HttpBearerAuthenticator}.
     */
    public static HttpBearerAuthenticator of(List<String> acceptableTokens) {
        return new HttpBearerAuthenticator(acceptableTokens);
    }

    /**
     * Создает новый экземпляр {@code HttpBearerAuthenticator} с одним допустимым токеном.
     *
     * @param acceptableToken допустимый токен.
     * @return новый экземпляр {@code HttpBearerAuthenticator}.
     */
    public static HttpBearerAuthenticator single(String acceptableToken) {
        return of(Collections.singletonList(acceptableToken));
    }

    private final List<String> acceptableTokens;

    /**
     * Аутентифицирует запрос, используя метод HTTP Bearer.
     * <p>
     * Проверяет, использует ли запрос метод аутентификации BEARER, и сравнивает предоставленный токен
     * с заранее определенным списком допустимых токенов.
     * </p>
     *
     * @param request запрос, который необходимо аутентифицировать.
     * @return результат аутентификации в виде {@code ApprovalResult}.
     */
    @Override
    public ApprovalResult authenticate(UnapprovedRequest request) {
        if (request.getAuthentication() != Authentication.BEARER) {
            return ApprovalResult.forbidden();
        }

        HttpCredentials credentials = request.getRequestCredentials();
        Token token = credentials.getToken();

        for (String acceptableTokenString : acceptableTokens) {
            if (Objects.equals(Token.of(acceptableTokenString), token)) {
                return ApprovalResult.approve();
            }
        }
        return ApprovalResult.forbidden();
    }
}
