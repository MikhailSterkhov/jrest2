package com.jrest.mvc.model;

import lombok.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseCode {

    public static ResponseCode fromCode(int httpCode) {
        return new ResponseCode(httpCode);
    }

    public static final ResponseCode SUCCESS = fromCode(HttpURLConnection.HTTP_OK);
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

    //

    private final int code;

    public ResponseCode add(int add) {
        return new ResponseCode(code + add);
    }

    public ResponseCode subtract(int subtract) {
        return new ResponseCode(code - subtract);
    }

    public ResponseCode set(int value) {
        return new ResponseCode(value);
    }

    public String getMessage() {
        return getDefaultMessage(this);
    }

    @Override
    public String toString() {
        return String.format("%d %s", getCode(), getMessage());
    }

    //

    private static final Map<Integer, String> MESSAGES_BY_CODE = new HashMap<>();
    private static final String MESSAGE_NOT_FOUND = "Unsupported Response-Code";

    static {
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

    public static String getDefaultMessage(ResponseCode responseCode) {
        return MESSAGES_BY_CODE.getOrDefault(responseCode.code, MESSAGE_NOT_FOUND);
    }
}
