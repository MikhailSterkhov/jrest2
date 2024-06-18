package com.jrest.mvc.model.authentication.defaults;

import com.jrest.mvc.model.authentication.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * Реализация аутентификации HTTP Basic.
 * <p>
 * Этот класс реализует аутентификацию HTTP Basic, проверяя имя пользователя и пароль.
 * Он сравнивает предоставленные учетные данные с заранее определенными учетными данными
 * и возвращает результат аутентификации.
 * </p>
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpBasicAuthenticator implements HttpAuthenticator {

    /**
     * Создает новый экземпляр {@code HttpBasicAuthenticator} с предоставленными учетными данными.
     *
     * @param approvalToken учетные данные в формате {@code UsernameAndPassword}, которые будут использоваться для аутентификации.
     * @return новый экземпляр {@code HttpBasicAuthenticator}.
     */
    public static HttpBasicAuthenticator of(Token.UsernameAndPassword approvalToken) {
        return new HttpBasicAuthenticator(approvalToken);
    }

    private final Token.UsernameAndPassword approvalToken;

    /**
     * Аутентифицирует запрос, используя метод HTTP Basic.
     * <p>
     * Проверяет, использует ли запрос метод аутентификации BASIC, декодирует учетные данные,
     * и сравнивает их с заранее определенными учетными данными.
     * </p>
     *
     * @param request запрос, который необходимо аутентифицировать.
     * @return результат аутентификации в виде {@code ApprovalResult}.
     */
    @Override
    public ApprovalResult authenticate(UnapprovedRequest request) {
        if (request.getAuthentication() != Authentication.BASIC) {
            return ApprovalResult.forbidden();
        }

        HttpCredentials credentials = request.getRequestCredentials();
        if (!credentials.isDecoded()) {
            credentials = credentials.tryDecode();
        }

        Token token = credentials.getToken();
        Token.UsernameAndPassword usernameAndPassword = token.readBasicCredentials();

        return ApprovalResult.bool(Objects.equals(usernameAndPassword, approvalToken));
    }
}
