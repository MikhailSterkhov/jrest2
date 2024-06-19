package com.jrest.http.server.authentication;

import com.jrest.mvc.model.authentication.Authentication;
import com.jrest.mvc.model.authentication.HttpAuthenticator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Контейнер для управления аутентификаторами, связанными с различными типами аутентификации.
 * <p>
 * Этот класс предоставляет методы для добавления, установки, удаления и получения аутентификаторов,
 * а также для очистки всех аутентификаторов.
 * </p>
 */
public final class AuthenticatorContainer {

    private final Map<Authentication, List<HttpAuthenticator>> authenticatorsMap =
            Collections.synchronizedMap(new HashMap<>());

    /**
     * Добавляет аутентификатор для указанного типа аутентификации.
     *
     * @param authentication тип аутентификации.
     * @param authenticator  аутентификатор для добавления.
     */
    public void add(Authentication authentication, HttpAuthenticator authenticator) {
        List<HttpAuthenticator> authenticators = authenticatorsMap
                .computeIfAbsent(authentication, k -> new ArrayList<>());
        authenticators.add(authenticator);
    }

    /**
     * Устанавливает единственный аутентификатор для указанного типа аутентификации,
     * заменяя все ранее добавленные аутентификаторы.
     *
     * @param authentication тип аутентификации.
     * @param authenticator  аутентификатор для установки.
     */
    public void set(Authentication authentication, HttpAuthenticator authenticator) {
        authenticatorsMap.put(authentication, new ArrayList<>(
                Collections.singletonList(authenticator)));
    }

    /**
     * Удаляет все аутентификаторы, связанные с указанным типом аутентификации.
     *
     * @param authentication тип аутентификации.
     */
    public void removeAll(Authentication authentication) {
        authenticatorsMap.remove(authentication);
    }

    /**
     * Очищает контейнер, удаляя все аутентификаторы.
     */
    public void clear() {
        authenticatorsMap.clear();
    }

    /**
     * Возвращает неизменяемый список аутентификаторов, связанных с указанным типом аутентификации.
     *
     * @param authentication тип аутентификации.
     * @return список аутентификаторов.
     */
    public List<HttpAuthenticator> getAuthenticators(Authentication authentication) {
        return Collections.unmodifiableList(authenticatorsMap.getOrDefault(authentication,
                Collections.emptyList()));
    }

    /**
     * Возвращает неизменяемый список всех аутентификаторов, добавленных в контейнер.
     *
     * @return список всех аутентификаторов.
     */
    public List<HttpAuthenticator> getAllAuthenticators() {
        return Collections.unmodifiableList(authenticatorsMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }
}
