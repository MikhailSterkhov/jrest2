package com.jrest.http.server.test.server.type;

import com.jrest.http.server.test.entity.UserEntity;
import com.jrest.mvc.model.*;
import com.jrest.mvc.persistence.HttpAsync;
import com.jrest.mvc.persistence.HttpRequestMapping;
import com.jrest.mvc.persistence.HttpPost;
import com.jrest.mvc.persistence.HttpServer;

@HttpServer
public class HttpBrowserServerTest {

    @HttpRequestMapping
    public HttpBrowserResponse callbackAttachments(HttpBrowserRequestContext context) {
        HttpRequest httpRequest = context.getHttpRequest();
        String uri = httpRequest.getUri();

        return HttpBrowserResponse.builder()
                .code(ResponseCode.OK)
                .view(HttpBrowserView.builder()
                        .attachment(WebResource.fromClassLoader(uri + context.getRequestedAttachmentName()))
                        .build())
                .build();
    }

    @HttpAsync
    @HttpPost("/user")
    public HttpResponse doUserPost(HttpRequest request) {
        UserEntity entity = request.getContent().toEntity(UserEntity.class);

        System.out.println(entity);

        return HttpResponse.builder()
                .code(ResponseCode.CREATED)
                .content(Content.fromText("{\"status\": \"Success created\", \"user_id\": 1}"))
                .build();
    }
}
