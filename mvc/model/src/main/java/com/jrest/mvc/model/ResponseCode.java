package com.jrest.mvc.model;

import lombok.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс ResponseCode представляет HTTP коды ответа с их текстовыми описаниями.
 * Он предоставляет стандартные константы для наиболее часто используемых кодов ответа,
 * методы для создания объектов ResponseCode по коду и тексту, а также методы для
 * определения типа кода ответа (информационный, успешный, перенаправление, ошибка клиента,
 * ошибка сервера и другие).
 *
 * <p>Пример использования:
 * <pre>{@code
 * ResponseCode code = ResponseCode.OK;
 * System.out.println("Код ответа: " + code.getCode());
 * System.out.println("Сообщение: " + code.getMessage());
 * System.out.println("Тип кода: " + (code.isSuccessful() ? "Успешный" : "Ошибка"));
 * }</pre>
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseCode {

    // Стандартные коды ответа
    public static final ResponseCode OK = fromCode(HttpURLConnection.HTTP_OK);
    public static final ResponseCode CREATED = fromCode(HttpURLConnection.HTTP_CREATED);
    public static final ResponseCode NO_CONTENT = fromCode(HttpURLConnection.HTTP_NO_CONTENT);
    public static final ResponseCode ACCEPTED = fromCode(HttpURLConnection.HTTP_ACCEPTED);
    public static final ResponseCode NOT_AUTHORITATIVE = fromCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
    public static final ResponseCode RESET = fromCode(HttpURLConnection.HTTP_RESET);
    public static final ResponseCode PARTIAL = fromCode(HttpURLConnection.HTTP_PARTIAL);
    public static final ResponseCode MULTI_CHOICE = fromCode(HttpURLConnection.HTTP_MULT_CHOICE);
    public static final ResponseCode MOVED_PERMANENTLY = fromCode(HttpURLConnection.HTTP_MOVED_PERM);
    public static final ResponseCode MOVED_TEMPORARY = fromCode(HttpURLConnection.HTTP_MOVED_TEMP);
    public static final ResponseCode SEE_OTHER = fromCode(HttpURLConnection.HTTP_SEE_OTHER);
    public static final ResponseCode NOT_MODIFIED = fromCode(HttpURLConnection.HTTP_NOT_MODIFIED);
    public static final ResponseCode USE_PROXY = fromCode(HttpURLConnection.HTTP_USE_PROXY);
    public static final ResponseCode BAD_REQUEST = fromCode(HttpURLConnection.HTTP_BAD_REQUEST);
    public static final ResponseCode UNAUTHORIZED = fromCode(HttpURLConnection.HTTP_UNAUTHORIZED);
    public static final ResponseCode PAYMENT_REQUIRED = fromCode(HttpURLConnection.HTTP_PAYMENT_REQUIRED);
    public static final ResponseCode FORBIDDEN = fromCode(HttpURLConnection.HTTP_FORBIDDEN);
    public static final ResponseCode NOT_FOUND = fromCode(HttpURLConnection.HTTP_NOT_FOUND);
    public static final ResponseCode BAD_METHOD = fromCode(HttpURLConnection.HTTP_BAD_METHOD);
    public static final ResponseCode NOT_ACCEPTABLE = fromCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
    public static final ResponseCode PROXY_AUTH = fromCode(HttpURLConnection.HTTP_PROXY_AUTH);
    public static final ResponseCode CLIENT_TIMEOUT = fromCode(HttpURLConnection.HTTP_CLIENT_TIMEOUT);
    public static final ResponseCode CONFLICT = fromCode(HttpURLConnection.HTTP_CONFLICT);
    public static final ResponseCode GONE = fromCode(HttpURLConnection.HTTP_GONE);
    public static final ResponseCode LENGTH_REQUIRED = fromCode(HttpURLConnection.HTTP_LENGTH_REQUIRED);
    public static final ResponseCode PRECONDITION_FAILED = fromCode(HttpURLConnection.HTTP_PRECON_FAILED);
    public static final ResponseCode ENTITY_TOO_LARGE = fromCode(HttpURLConnection.HTTP_ENTITY_TOO_LARGE);
    public static final ResponseCode URI_TOO_LARGE = fromCode(HttpURLConnection.HTTP_REQ_TOO_LONG);
    public static final ResponseCode UNSUPPORTED_TYPE = fromCode(HttpURLConnection.HTTP_UNSUPPORTED_TYPE);
    public static final ResponseCode UNPROCESSABLE_ENTITY = fromCode(422);
    public static final ResponseCode INTERNAL_ERROR = fromCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    public static final ResponseCode NOT_IMPLEMENTED = fromCode(HttpURLConnection.HTTP_NOT_IMPLEMENTED);
    public static final ResponseCode BAD_GATEWAY = fromCode(HttpURLConnection.HTTP_BAD_GATEWAY);
    public static final ResponseCode UNAVAILABLE = fromCode(HttpURLConnection.HTTP_UNAVAILABLE);
    public static final ResponseCode GATEWAY_TIMEOUT = fromCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
    public static final ResponseCode INVALID_VERSION = fromCode(HttpURLConnection.HTTP_VERSION);

    // Биты для определения типа кода ответа
    private static final int INFORMATIONAL_BIT = 100;
    private static final int SUCCESSFUL_CODE_BIT = 200;
    private static final int REDIRECTION_CODE_BIT = 300;
    private static final int CLIENT_ERROR_CODE_BIT = 400;
    private static final int SERVER_ERROR_CODE_BIT = 500;

    private final int code;
    private final String message;

    /**
     * Создает объект ResponseCode на основе указанного HTTP кода и текстового сообщения.
     *
     * @param httpCode HTTP код ответа
     * @param message  текстовое сообщение
     * @return объект ResponseCode
     */
    public static ResponseCode fromCodeAndMessage(int httpCode, String message) {
        return new ResponseCode(httpCode, message);
    }

    /**
     * Создает объект ResponseCode на основе указанного HTTP кода.
     * Автоматически определяет соответствующее текстовое сообщение.
     *
     * @param httpCode HTTP код ответа
     * @return объект ResponseCode
     */
    public static ResponseCode fromCode(int httpCode) {
        return ResponseCode.fromCodeAndMessage(httpCode, getCodeMessage(httpCode));
    }

    /**
     * Создает объект ResponseCode на основе указанного HTTP кода и кеширует его сообщение,
     * если оно еще не было кешировано.
     *
     * @param httpCode HTTP код ответа
     * @param message  текстовое сообщение
     * @return объект ResponseCode
     */
    public static ResponseCode createAndCache(int httpCode, String message) {
        if (MESSAGES_BY_CODE == null) {
            initMessagesByCodes();
        }
        if (!MESSAGES_BY_CODE.containsKey(httpCode)) {
            MESSAGES_BY_CODE.put(httpCode, message);
        }
        return ResponseCode.fromCodeAndMessage(httpCode, message);
    }

    /**
     * Возвращает тип бита кода ответа (информационный, успешный, перенаправление, ошибка клиента,
     * ошибка сервера).
     *
     * @return тип бита кода ответа
     */
    public int getBit() {
        return Math.min(code, SERVER_ERROR_CODE_BIT) / 100 * 100;
    }

    /**
     * Проверяет, является ли код ответа неизвестным типом.
     *
     * @return true, если код ответа неизвестный; false в противном случае
     */
    public boolean isUnknownType() {
        return code < INFORMATIONAL_BIT || code >= (SERVER_ERROR_CODE_BIT + INFORMATIONAL_BIT);
    }

    /**
     * Проверяет, является ли код ответа информационным.
     *
     * @return true, если код ответа информационный; false в противном случае
     */
    public boolean isInformational() {
        return Objects.equals(INFORMATIONAL_BIT, getBit());
    }

    /**
     * Проверяет, является ли код ответа успешным.
     *
     * @return true, если код ответа успешный; false в противном случае
     */
    public boolean isSuccessful() {
        return Objects.equals(SUCCESSFUL_CODE_BIT, getBit());
    }

    /**
     * Проверяет, является ли код ответа перенаправлением.
     *
     * @return true, если код ответа перенаправление; false в противном случае
     */
    public boolean isRedirection() {
        return Objects.equals(REDIRECTION_CODE_BIT, getBit());
    }

    /**
     * Проверяет, является ли код ответа ошибкой клиента.
     *
     * @return true, если код ответа ошибка клиента; false в противном случае
     */
    public boolean isClientError() {
        return Objects.equals(CLIENT_ERROR_CODE_BIT, getBit());
    }

    /**
     * Проверяет, является ли код ответа ошибкой сервера.
     *
     * @return true, если код ответа ошибка сервера; false в противном случае
     */
    public boolean isServerError() {
        return Objects.equals(SERVER_ERROR_CODE_BIT, getBit());
    }

    /**
     * Возвращает строковое представление объекта ResponseCode в формате "код сообщение".
     *
     * @return строковое представление объекта ResponseCode
     */
    @Override
    public String toString() {
        return String.format("%d %s", getCode(), getMessage());
    }

    private static Map<Integer, String> MESSAGES_BY_CODE;

    private static final String MESSAGE_NOT_FOUND = "Unsupported Response-Code";

    private static void initMessagesByCodes() {
        MESSAGES_BY_CODE = new HashMap<>();
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_OK, "OK");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_CREATED, "Created");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NO_CONTENT, "No Content");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_ACCEPTED, "Accepted");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_AUTHORITATIVE, "Non-Authoritative Information");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_RESET, "Reset Content");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_PARTIAL, "Partial Content");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_MULT_CHOICE, "Multiple Choices");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_MOVED_PERM, "Moved Permanently");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_MOVED_TEMP, "Moved Temporary");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_SEE_OTHER, "See Other");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_MODIFIED, "Not Modified");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_USE_PROXY, "Use Proxy");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_BAD_REQUEST, "Bad Request");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_UNAUTHORIZED, "Unauthorized");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_PAYMENT_REQUIRED, "Payment Required");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_FORBIDDEN, "Forbidden");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_FOUND, "Not Found");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_BAD_METHOD, "Bad Method");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_ACCEPTABLE, "Not Acceptable");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_PROXY_AUTH, "Proxy Authentication Required");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_CLIENT_TIMEOUT, "Request Time-Out");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_CONFLICT, "Conflict");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_GONE, "Gone");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_LENGTH_REQUIRED, "Length Required");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_PRECON_FAILED, "Precondition Failed");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_ENTITY_TOO_LARGE, "Request Entity Too Large");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_REQ_TOO_LONG, "Request-URI Too Large");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_UNSUPPORTED_TYPE, "Unsupported Media Type");
        MESSAGES_BY_CODE.put(422, "Unprocessable Entity");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_INTERNAL_ERROR, "Internal Server Error");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_IMPLEMENTED, "Not Implemented");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_BAD_GATEWAY, "Bad Gateway");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_UNAVAILABLE, "Service Unavailable");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, "Gateway Timeout");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_VERSION, "HTTP Version Not Supported");
    }

    public static String getCodeMessage(ResponseCode responseCode) {
        return getCodeMessage(responseCode.getCode());
    }

    private static String getCodeMessage(int responseCode) {
        if (MESSAGES_BY_CODE == null || MESSAGES_BY_CODE.isEmpty()) {
            initMessagesByCodes();
        }
        return MESSAGES_BY_CODE.getOrDefault(responseCode, MESSAGE_NOT_FOUND);
    }
}
