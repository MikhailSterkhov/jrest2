package com.jrest.http.server.authentication;

import com.jrest.mvc.model.authentication.Authentication;
import com.jrest.mvc.model.authentication.HttpAuthenticator;

import java.util.*;
import java.util.stream.Collectors;

public final class AuthenticatorContainer {

    private final Map<Authentication, List<HttpAuthenticator>> authenticatorsMap =
            Collections.synchronizedMap(new HashMap<>());

    public void add(Authentication authentication, HttpAuthenticator authenticator) {
        List<HttpAuthenticator> authenticators = authenticatorsMap
                .computeIfAbsent(authentication, k -> new ArrayList<>());
        authenticators.add(authenticator);
    }

    public void set(Authentication authentication, HttpAuthenticator authenticator) {
        authenticatorsMap.put(authentication, new ArrayList<>(
                Collections.singletonList(authenticator)));
    }

    public void removeAll(Authentication authentication) {
        authenticatorsMap.remove(authentication);
    }

    public void clear() {
        authenticatorsMap.clear();
    }

    public List<HttpAuthenticator> getAuthenticators(Authentication authentication) {
        return Collections.unmodifiableList(authenticatorsMap.getOrDefault(authentication,
                Collections.emptyList()));
    }

    public List<HttpAuthenticator> getAllAuthenticators() {
        return Collections.unmodifiableList(authenticatorsMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }
}
