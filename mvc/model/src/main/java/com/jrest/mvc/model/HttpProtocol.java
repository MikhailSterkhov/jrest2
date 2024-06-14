package com.jrest.mvc.model;

import lombok.*;

/**
 * Представляет HTTP протокол с указанным именем, основной и минорной версиями.
 * Поддерживает статические методы для создания и чтения HTTP протоколов.
 */
@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpProtocol {

    /**
     * HTTP протокол версии 1.0.
     */
    public static final HttpProtocol HTTP_1_0 = read("HTTP/1.0");

    /**
     * HTTP протокол версии 1.1.
     */
    public static final HttpProtocol HTTP_1_1 = read("HTTP/1.1");

    /**
     * Создает экземпляр HttpProtocol на основе переданной строки протокола.
     *
     * @param protocol строка, представляющая протокол (например, "HTTP/1.1").
     * @return экземпляр HttpProtocol с указанным именем, основной и минорной версиями.
     */
    public static HttpProtocol read(String protocol) {
        String protocolName = protocol.substring(0, protocol.indexOf("/"));
        String version = protocol.substring(protocolName.length() + 1);
        String[] versionData = version.split("\\.");
        return new HttpProtocol(
                protocolName,
                Integer.parseInt(versionData[0]),
                versionData.length > 1 ? Integer.parseInt(versionData[1]) : 0);
    }

    private final String name;         // Имя протокола (например, "HTTP")
    private final int versionMajor;    // Основная версия протокола (например, 1)
    private final int versionMinor;    // Минорная версия протокола (например, 1)

    /**
     * Возвращает строковое представление HTTP протокола в формате "имя/основная.минорная".
     *
     * @return строковое представление HTTP протокола.
     */
    @Override
    public String toString() {
        return String.format("%s/%d.%d", name, versionMajor, versionMinor);
    }
}
