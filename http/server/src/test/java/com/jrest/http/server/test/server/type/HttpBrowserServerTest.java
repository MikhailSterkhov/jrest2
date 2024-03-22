package com.jrest.http.server.test.server.type;

import com.jrest.http.server.test.entity.UserEntity;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.ResponseCode;
import com.jrest.mvc.model.HttpBrowserRequestContext;
import com.jrest.mvc.model.HttpBrowserResponse;
import com.jrest.mvc.model.HttpBrowserView;
import com.jrest.mvc.model.WebResource;
import com.jrest.mvc.model.ContentBody;
import com.jrest.mvc.persistence.HttpAsync;
import com.jrest.mvc.persistence.HttpBrowserRequest;
import com.jrest.mvc.persistence.HttpPost;
import com.jrest.mvc.persistence.HttpServer;

@HttpServer
public class HttpBrowserServerTest {

    @HttpBrowserRequest
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
        UserEntity entity = request.getContentBody().toEntity(UserEntity.class);

        System.out.println(entity);

        return HttpResponse.builder()
                .code(ResponseCode.CREATED)
                .content(ContentBody.fromText("{\"status\": \"Success created\", \"user_id\": 1}"))
                .build();
    }
}
