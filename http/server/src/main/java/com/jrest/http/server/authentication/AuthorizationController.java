package com.jrest.http.server.authentication;

import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.authentication.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Контроллер для управления аутентификацией HTTP-запросов.
 * <p>
 * Этот класс проверяет наличие аутентификации в запросе, аутентифицирует запросы с помощью предоставленных аутентификаторов,
 * находит тип аутентификации и извлекает токен из заголовков запроса.
 * </p>
 */
public final class AuthorizationController {

    private static final List<String> AUTHENTICATION_HEADERS =
            Arrays.asList(
                    Headers.Def.AUTHORIZATION,
                    Headers.Def.WWW_AUTHENTICATE,
                    Headers.Def.PROXY_AUTHENTICATE,
                    Headers.Def.PROXY_AUTHORIZATION
            );

    /**
     * Проверяет, содержит ли HTTP-запрос заголовки аутентификации.
     *
     * @param httpRequest HTTP-запрос.
     * @return true, если запрос содержит заголовки аутентификации, иначе false.
     */
    public boolean hasAuthentication(HttpRequest httpRequest) {
        Set<String> requestHeadersKeys = httpRequest.getHeaders().keys();
        for (String authenticationHeader : AUTHENTICATION_HEADERS) {
            if (requestHeadersKeys.contains(authenticationHeader)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Аутентифицирует HTTP-запрос с использованием предоставленного типа аутентификации и списка аутентификаторов.
     *
     * @param httpRequest    HTTP-запрос.
     * @param authentication Тип аутентификации.
     * @param authenticators Список аутентификаторов.
     * @return Результат аутентификации.
     */
    public ApprovalResult authenticate(HttpRequest httpRequest, Authentication authentication, List<HttpAuthenticator> authenticators) {
        if (authenticators == null || authenticators.isEmpty()) {
            throw new HttpAuthorizationException("authenticator not found for [" + httpRequest.getMethod() + " " + httpRequest.getUrl() + "]");
        }

        if (authentication == Authentication.NO_AUTHENTICATION) {
            return ApprovalResult.skip();
        }

        Token requestToken = readRequestToken(httpRequest, authentication);
        if (requestToken == null) {
            return ApprovalResult.forbidden();
        }

        UnapprovedRequest unapprovedRequest =
                UnapprovedRequest.builder()
                        .requestHeaders(httpRequest.getHeaders())
                        .authentication(authentication)
                        .requestCredentials(HttpCredentials.builder()
                                .token(requestToken)
                                .hash(authentication.getHash())
                                .encoded(authentication.getHash() != AuthenticationHash.NO_HASH)
                                .decoded(false)
                                .build())
                        .build();

        return tryAuthenticate(unapprovedRequest, authenticators);
    }

    private ApprovalResult tryAuthenticate(UnapprovedRequest unapprovedRequest, List<HttpAuthenticator> authenticators) {
        ApprovalResult result = null;
        for (HttpAuthenticator authenticator : authenticators) {
            result = authenticator.authenticate(unapprovedRequest);
            if (result.isApproved()) {
                return result;
            }
        }
        return result;
    }

    /**
     * Находит тип аутентификации в HTTP-запросе.
     *
     * @param httpRequest HTTP-запрос.
     * @return Тип аутентификации.
     */
    public Authentication findAuthentication(HttpRequest httpRequest) {
        Headers requestHeaders = httpRequest.getHeaders();
        for (Authentication authentication : AuthenticationTypes.values()) {
            if (authentication == Authentication.NO_AUTHENTICATION) {
                continue;
            }
            for (String acceptableHeader : authentication.getAcceptableHeaders()) {
                String first = requestHeaders.getFirst(acceptableHeader);
                if (requestHeaders.has(acceptableHeader) && first != null && !first.isEmpty()) {
                    if (first.startsWith(authentication.getName())) {
                        return authentication;
                    }
                }
            }
        }
        return Authentication.NO_AUTHENTICATION;
    }

    private Token readRequestToken(HttpRequest httpRequest, Authentication authentication) {
        List<String> acceptableHeaders = authentication.getAcceptableHeaders();
        for (String acceptableHeader : acceptableHeaders) {
            String headerValue = httpRequest.getHeaders().getFirst(acceptableHeader);
            if (headerValue != null) {
                headerValue = headerValue.substring(headerValue.indexOf(" ") + 1);
                return Token.of(headerValue);
            }
        }
        return null;
    }
}
