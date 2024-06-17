package com.jrest.mvc.model;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpResponse {

    public static HttpResponse of(ResponseCode responseCode) {
        return HttpResponse.builder()
                .code(responseCode)
                .build();
    }

    public static HttpResponse of(ResponseCode responseCode, Content content) {
        return HttpResponse.builder()
                .code(responseCode)
                .content(content)
                .build();
    }

    public static HttpResponse ok() {
        return of(ResponseCode.OK);
    }

    public static HttpResponse ok(Content content) {
        return of(ResponseCode.OK, content);
    }

    public static HttpResponse noContent() {
        return of(ResponseCode.NO_CONTENT);
    }

    public static HttpResponse noContent(Content content) {
        return of(ResponseCode.NO_CONTENT, content);
    }

    public static HttpResponse notFound() {
        return of(ResponseCode.NOT_FOUND);
    }

    public static HttpResponse notFound(Content content) {
        return of(ResponseCode.NOT_FOUND, content);
    }

    public static HttpResponse badRequest() {
        return of(ResponseCode.BAD_REQUEST);
    }

    public static HttpResponse badRequest(Content content) {
        return of(ResponseCode.BAD_REQUEST, content);
    }

    public static HttpResponse internalError() {
        return of(ResponseCode.INTERNAL_ERROR);
    }

    public static HttpResponse internalError(Content content) {
        return of(ResponseCode.INTERNAL_ERROR, content);
    }

    private HttpProtocol protocol;

    private ResponseCode code;

    private Headers headers;
    private Content content;
}
