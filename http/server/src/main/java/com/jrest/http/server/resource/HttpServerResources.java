package com.jrest.http.server.resource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServerResources {

    public static HttpServerResources create() {
        return new HttpServerResources();
    }

    private final Set<HttpResourceUnit> units = new CopyOnWriteArraySet<>();

    public void register(HttpResourceUnit resourceUnit) {
        if (!units.add(resourceUnit)) {
            throw new HttpServerResourceException("Resource uri `" + resourceUnit.getPath().toURI() + "` is already registered");
        }
    }

    public void unregister(HttpResourceUnit resourceUnit) {
        if (!units.remove(resourceUnit)) {
            throw new HttpServerResourceException("Resource uri `" + resourceUnit.getPath().toURI() + "` is not registered");
        }
    }

    public Set<HttpResourceUnit> getAllResourcesUnits() {
        return Collections.unmodifiableSet(units);
    }
}
