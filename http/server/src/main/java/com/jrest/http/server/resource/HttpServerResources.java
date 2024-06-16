package com.jrest.http.server.resource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServerResources {

    public static HttpServerResources create() {
        return new HttpServerResources();
    }

    private final List<HttpResourceUnit> units = new CopyOnWriteArrayList<>();

    public void register(HttpResourceUnit resourceUnit) {
        units.add(resourceUnit);
    }

    public void unregister(HttpResourceUnit resourceUnit) {
        units.remove(resourceUnit);
    }

    public List<HttpResourceUnit> getAllResourcesUnits() {
        return Collections.unmodifiableList(units);
    }
}
