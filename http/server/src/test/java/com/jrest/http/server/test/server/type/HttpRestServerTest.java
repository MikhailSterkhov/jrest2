package com.jrest.http.server.test.server.type;

import com.jrest.http.server.test.entity.UserEntity;
import com.jrest.http.server.test.entity.UserJobInfo;
import com.jrest.mvc.model.*;
import com.jrest.mvc.persistence.HttpGet;
import com.jrest.mvc.persistence.HttpRequestMapping;
import com.jrest.mvc.persistence.HttpServer;

import java.util.Optional;

@HttpServer
public class HttpRestServerTest {

    @HttpRequestMapping
    public void before(Provider<HttpRequest> provider) {
        HttpRequest httpRequest = provider.get();
        provider.set(httpRequest.toBuilder()
                .headers(httpRequest.getHeaders()
                        .set(Headers.Defaults.CONTENT_TYPE, ContentType.APPLICATION_JSON)
                        .set(Headers.Defaults.USER_AGENT, "Mikhail Sterkhov"))
                .build());
    }

    @HttpGet("/user/{id}")
    public HttpResponse doGet(HttpRequest request) {
        Attributes attributes = request.getAttributes();
        Optional<Integer> attributeIdOptional = attributes.getInteger("id");

        if (!attributeIdOptional.isPresent()) {
            return HttpResponse.builder()
                    .code(ResponseCode.BAD_REQUEST)
                    .build();
        }
        return HttpResponse.builder()
                .code(ResponseCode.OK)
                .content(Content.fromEntity(
                        UserEntity.builder()
                                .id(attributeIdOptional.get())
                                .jobInfo(UserJobInfo.builder()
                                        .company("Microsoft Corporation")
                                        .website("https://www.microsoft.com/")
                                        .profession("Developer C#")
                                        .salary(3500)
                                        .build())
                                .firstName("Piter")
                                .lastName("Harrison")
                                .build()
                ))
                .build();
    }
}
