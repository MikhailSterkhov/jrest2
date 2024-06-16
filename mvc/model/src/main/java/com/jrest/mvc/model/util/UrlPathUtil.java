package com.jrest.mvc.model.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlPathUtil {

    private static final String PROTOCOL_DELIMITER = "://";
    private static final String PATH_DELIMITER = "/";

    public String stripPath(String url) {
        if (!url.contains(PROTOCOL_DELIMITER)) {
            return url;
        }

        int hostFirstIndex = url.indexOf(PROTOCOL_DELIMITER);
        String hostWithUri = url.substring(hostFirstIndex + 3);

        return hostWithUri.substring(hostWithUri.indexOf(PATH_DELIMITER));
    }
}
