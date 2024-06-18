package com.jrest.mvc.model.util;

import lombok.experimental.UtilityClass;

/**
 * Утилитарный класс для работы с URL и извлечения пути.
 * <p>
 * Этот класс содержит методы для обработки URL и извлечения пути из полного URL.
 * </p>
 */
@UtilityClass
public class UrlPathUtil {

    private static final String PROTOCOL_DELIMITER = "://";
    private static final String PATH_DELIMITER = "/";

    /**
     * Извлекает путь из заданного URL.
     * <p>
     * Этот метод удаляет протокол и хост из URL и возвращает только путь.
     * Например, для URL "https://example.com/path/to/resource" метод вернет "/path/to/resource".
     * </p>
     *
     * @param url полный URL, из которого нужно извлечь путь
     * @return путь из URL
     */
    public String stripPath(String url) {
        if (!url.contains(PROTOCOL_DELIMITER)) {
            return url;
        }

        int hostFirstIndex = url.indexOf(PROTOCOL_DELIMITER);
        String hostWithUri = url.substring(hostFirstIndex + 3);

        if (!hostWithUri.contains(PATH_DELIMITER)) {
            return PATH_DELIMITER;
        }

        return hostWithUri.substring(hostWithUri.indexOf(PATH_DELIMITER));
    }
}
