package com.jrest.mvc.model.authentication;

import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.authentication.defaults.HttpBasicAuthenticator;
import com.jrest.mvc.model.authentication.defaults.HttpBearerAuthenticator;
import lombok.*;

import java.util.List;

/**
 * Представляет неутвержденный HTTP-запрос, который требуется аутентифицировать.
 * <p>
 * Этот класс содержит заголовки запроса, тип аутентификации и учетные данные.
 * Используется в процессе аутентификации для передачи
 * информации о запросе и учетных данных аутентификатору.
 * </p>
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class UnapprovedRequest {

    /**
     * Заголовки HTTP-запроса.
     */
    private final Headers requestHeaders;

    /**
     * Тип аутентификации, используемый в запросе.
     */
    private final Authentication authentication;

    /**
     * Учетные данные для аутентификации, предоставленные из запроса.
     */
    private final HttpCredentials requestCredentials;

    /**
     * Пытается аутентифицировать запрос с помощью предоставленного аутентификатора.
     *
     * @param authenticator аутентификатор, который будет использоваться для аутентификации.
     * @return результат аутентификации в виде {@code ApprovalResult}.
     */
    public ApprovalResult tryAuthenticateAnother(HttpAuthenticator authenticator) {
        return authenticator.authenticate(this);
    }

    /**
     * Выполняет базовую аутентификацию с использованием имени пользователя и пароля.
     *
     * @param usernameAndPassword имя пользователя и пароль для аутентификации.
     * @return результат аутентификации в виде {@code ApprovalResult}.
     */
    public ApprovalResult basicAuthenticate(Token.UsernameAndPassword usernameAndPassword) {
        return tryAuthenticateAnother(HttpBasicAuthenticator.of(usernameAndPassword));
    }

    /**
     * Выполняет аутентификацию Bearer с использованием списка допустимых токенов.
     *
     * @param acceptableTokens список допустимых токенов.
     * @return результат аутентификации в виде {@code ApprovalResult}.
     */
    public ApprovalResult bearerAuthenticate(List<String> acceptableTokens) {
        return tryAuthenticateAnother(HttpBearerAuthenticator.of(acceptableTokens));
    }

    /**
     * Выполняет аутентификацию Bearer с использованием одного допустимого токена.
     *
     * @param acceptableToken допустимый токен.
     * @return результат аутентификации в виде {@code ApprovalResult}.
     */
    public ApprovalResult bearerAuthenticate(String acceptableToken) {
        return tryAuthenticateAnother(HttpBearerAuthenticator.single(acceptableToken));
    }
}
