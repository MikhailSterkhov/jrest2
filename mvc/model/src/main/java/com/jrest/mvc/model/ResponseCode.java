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
    public static final ResponseCode CONTINUE = fromCode(100);
    public static final ResponseCode SWITCHING_PROTOCOLS = fromCode(101);
    public static final ResponseCode PROCESSING = fromCode(102);
    public static final ResponseCode EARLY_HINTS = fromCode(103);

    public static final ResponseCode OK = fromCode(HttpURLConnection.HTTP_OK);
    public static final ResponseCode CREATED = fromCode(HttpURLConnection.HTTP_CREATED);
    public static final ResponseCode NO_CONTENT = fromCode(HttpURLConnection.HTTP_NO_CONTENT);
    public static final ResponseCode ACCEPTED = fromCode(HttpURLConnection.HTTP_ACCEPTED);
    public static final ResponseCode NOT_AUTHORITATIVE = fromCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
    public static final ResponseCode RESET = fromCode(HttpURLConnection.HTTP_RESET);
    public static final ResponseCode PARTIAL = fromCode(HttpURLConnection.HTTP_PARTIAL);
    public static final ResponseCode MULTI_STATUS = fromCode(207);
    public static final ResponseCode ALREADY_REPORTED = fromCode(208);
    public static final ResponseCode IM_USED = fromCode(226);

    public static final ResponseCode MULTI_CHOICE = fromCode(HttpURLConnection.HTTP_MULT_CHOICE);
    public static final ResponseCode MOVED_PERMANENTLY = fromCode(HttpURLConnection.HTTP_MOVED_PERM);
    public static final ResponseCode MOVED_TEMPORARY = fromCode(HttpURLConnection.HTTP_MOVED_TEMP);
    public static final ResponseCode SEE_OTHER = fromCode(HttpURLConnection.HTTP_SEE_OTHER);
    public static final ResponseCode NOT_MODIFIED = fromCode(HttpURLConnection.HTTP_NOT_MODIFIED);
    public static final ResponseCode USE_PROXY = fromCode(HttpURLConnection.HTTP_USE_PROXY);
    public static final ResponseCode TEMPORARY_REDIRECT = fromCode(307);
    public static final ResponseCode PERMANENT_REDIRECT = fromCode(308);

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
    public static final ResponseCode RANGE_NOT_SATISFIABLE = fromCode(416);
    public static final ResponseCode EXPECTATION_FAILED = fromCode(417);
    public static final ResponseCode IM_A_TEAPOT = fromCode(418);
    public static final ResponseCode AUTHENTICATION_TIMEOUT = fromCode(419);
    public static final ResponseCode MISDIRECTED_REQUEST = fromCode(421);
    public static final ResponseCode UNPROCESSABLE_ENTITY = fromCode(422);
    public static final ResponseCode LOCKED = fromCode(423);
    public static final ResponseCode FAILED_DEPENDENCY = fromCode(424);
    public static final ResponseCode TOO_EARLY = fromCode(425);
    public static final ResponseCode UPGRADE_REQUIRED = fromCode(426);
    public static final ResponseCode PRECONDITION_REQUIRED = fromCode(428);
    public static final ResponseCode TOO_MANY_REQUESTS = fromCode(429);
    public static final ResponseCode REQUEST_HEADER_FIELDS_TOO_LARGE = fromCode(431);
    public static final ResponseCode RETRY_WITH = fromCode(449);
    public static final ResponseCode UNAVAILABLE_FOR_LEGAL_REASONS = fromCode(451);
    public static final ResponseCode CLIENT_CLOSED_REQUEST = fromCode(499);

    public static final ResponseCode INTERNAL_ERROR = fromCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    public static final ResponseCode NOT_IMPLEMENTED = fromCode(HttpURLConnection.HTTP_NOT_IMPLEMENTED);
    public static final ResponseCode BAD_GATEWAY = fromCode(HttpURLConnection.HTTP_BAD_GATEWAY);
    public static final ResponseCode UNAVAILABLE = fromCode(HttpURLConnection.HTTP_UNAVAILABLE);
    public static final ResponseCode GATEWAY_TIMEOUT = fromCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
    public static final ResponseCode INVALID_VERSION = fromCode(HttpURLConnection.HTTP_VERSION);
    public static final ResponseCode VARIANT_ALSO_NEGOTIATES = fromCode(506);
    public static final ResponseCode INSUFFICIENT_STORAGE = fromCode(507);
    public static final ResponseCode LOOP_DETECTED = fromCode(508);
    public static final ResponseCode BANDWIDTH_LIMIT_EXCEEDED = fromCode(509);
    public static final ResponseCode NOT_EXTENDED = fromCode(510);
    public static final ResponseCode NETWORK_AUTHENTICATION_REQUIRED = fromCode(511);
    public static final ResponseCode UNKNOWN_ERROR = fromCode(520);
    public static final ResponseCode WEB_SERVER_IS_DOWN = fromCode(521);
    public static final ResponseCode CONNECTION_TIMED_OUT = fromCode(522);
    public static final ResponseCode ORIGIN_IS_UNREACHABLE = fromCode(523);
    public static final ResponseCode A_TIMEOUT_OCCURRED = fromCode(524);
    public static final ResponseCode SSL_HANDSHAKE_FAILED = fromCode(525);
    public static final ResponseCode INVALID_SSL_CERTIFICATE = fromCode(526);

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
     * Проверяет, является ли код ответа ошибкой в целом.
     *
     * @return true, если код ответа ошибка в целом; false в противном случае
     */
    public boolean isError() {
        return isClientError() || isServerError();
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
        MESSAGES_BY_CODE.put(100, "Continue");
        MESSAGES_BY_CODE.put(101, "Switching Protocols");
        MESSAGES_BY_CODE.put(102, "Processing");
        MESSAGES_BY_CODE.put(103, "Early Hints");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_OK, "OK");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_CREATED, "Created");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NO_CONTENT, "No Content");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_ACCEPTED, "Accepted");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_AUTHORITATIVE, "Non-Authoritative Information");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_RESET, "Reset Content");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_PARTIAL, "Partial Content");
        MESSAGES_BY_CODE.put(207, "Multi-Status");
        MESSAGES_BY_CODE.put(208, "Already Reported");
        MESSAGES_BY_CODE.put(226, "IM Used");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_MULT_CHOICE, "Multiple Choices");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_MOVED_PERM, "Moved Permanently");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_MOVED_TEMP, "Moved Temporary");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_SEE_OTHER, "See Other");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_MODIFIED, "Not Modified");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_USE_PROXY, "Use Proxy");
        MESSAGES_BY_CODE.put(307, "Temporary Redirect");
        MESSAGES_BY_CODE.put(308, "Permanent Redirect");
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
        MESSAGES_BY_CODE.put(416, "Range Not Satisfiable");
        MESSAGES_BY_CODE.put(417, "Expectation Failed");
        MESSAGES_BY_CODE.put(418, "I’m a teapot");
        MESSAGES_BY_CODE.put(419, "Authentication Timeout");
        MESSAGES_BY_CODE.put(421, "Misdirected Request");
        MESSAGES_BY_CODE.put(422, "Unprocessable Entity");
        MESSAGES_BY_CODE.put(423, "Locked");
        MESSAGES_BY_CODE.put(424, "Failed Dependency");
        MESSAGES_BY_CODE.put(425, "Too Early");
        MESSAGES_BY_CODE.put(426, "Upgrade Required");
        MESSAGES_BY_CODE.put(428, "Precondition Required");
        MESSAGES_BY_CODE.put(429, "Too Many Requests");
        MESSAGES_BY_CODE.put(431, "Request Header Fields Too Large");
        MESSAGES_BY_CODE.put(449, "Retry With");
        MESSAGES_BY_CODE.put(451, "Unavailable For Legal Reasons");
        MESSAGES_BY_CODE.put(499, "Client Closed Request");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_INTERNAL_ERROR, "Internal Server Error");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_NOT_IMPLEMENTED, "Not Implemented");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_BAD_GATEWAY, "Bad Gateway");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_UNAVAILABLE, "Service Unavailable");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, "Gateway Timeout");
        MESSAGES_BY_CODE.put(HttpURLConnection.HTTP_VERSION, "HTTP Version Not Supported");
        MESSAGES_BY_CODE.put(506, "Variant Also Negotiates");
        MESSAGES_BY_CODE.put(507, "Insufficient Storage");
        MESSAGES_BY_CODE.put(508, "Loop Detected");
        MESSAGES_BY_CODE.put(509, "Bandwidth Limit Exceeded");
        MESSAGES_BY_CODE.put(510, "Not Extended");
        MESSAGES_BY_CODE.put(511, "Network Authentication Required");
        MESSAGES_BY_CODE.put(520, "Unknown Error");
        MESSAGES_BY_CODE.put(521, "Web Server Is Down");
        MESSAGES_BY_CODE.put(522, "Connection Timed Out");
        MESSAGES_BY_CODE.put(523, "Origin Is Unreachable");
        MESSAGES_BY_CODE.put(524, "A Timeout Occurred");
        MESSAGES_BY_CODE.put(525, "SSL Handshake Failed");
        MESSAGES_BY_CODE.put(526, "Invalid SSL Certificate");
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
