package com.jrest.mvc.model;

import lombok.*;

@Setter
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpResponse {

    public static HttpResponse ok() {
        return HttpResponse.builder()
                .code(ResponseCode.OK)
                .build();
    }

    public static HttpResponse ok(Content content) {
        return HttpResponse.builder()
                .code(ResponseCode.OK)
                .content(content)
                .build();
    }

    public static HttpResponse noContent() {
        return HttpResponse.builder()
                .code(ResponseCode.NO_CONTENT)
                .build();
    }

    public static HttpResponse noContent(Content content) {
        return HttpResponse.builder()
                .code(ResponseCode.NO_CONTENT)
                .content(content)
                .build();
    }

    public static HttpResponse notFound() {
        return HttpResponse.builder()
                .code(ResponseCode.NOT_FOUND)
                .build();
    }

    public static HttpResponse notFound(Content content) {
        return HttpResponse.builder()
                .code(ResponseCode.NOT_FOUND)
                .content(content)
                .build();
    }

    public static HttpResponse badRequest() {
        return HttpResponse.builder()
                .code(ResponseCode.BAD_REQUEST)
                .build();
    }

    public static HttpResponse badRequest(Content content) {
        return HttpResponse.builder()
                .code(ResponseCode.BAD_REQUEST)
                .content(content)
                .build();
    }

    public static HttpResponse internalError() {
        return HttpResponse.builder()
                .code(ResponseCode.INTERNAL_ERROR)
                .build();
    }

    public static HttpResponse internalError(Content content) {
        return HttpResponse.builder()
                .code(ResponseCode.INTERNAL_ERROR)
                .content(content)
                .build();
    }

    private HttpProtocol protocol;

    private ResponseCode code;

    private Headers headers;
    private Content content;
}
