package com.jrest.test.http.server.server.type;

import com.jrest.test.http.server.employee.Employee;
import com.jrest.test.http.server.employee.EmployeeJob;
import com.jrest.mvc.model.*;
import com.jrest.mvc.persistence.*;

import java.util.Optional;

@HttpServer
public class HttpServerRepository {

    @HttpBeforeExecution
    public void before(HttpRequest httpRequest) {
        Headers headers = httpRequest.getHeaders()
                .set(Headers.Def.CONTENT_TYPE, ContentType.APPLICATION_JSON)
                .set(Headers.Def.USER_AGENT, "Mikhail Sterkhov");

        httpRequest.setHeaders(headers);
    }

    @HttpGet("/employee")
    public HttpResponse doGet(HttpRequest request) {
        Attributes attributes = request.getAttributes();
        Optional<Integer> attributeIdOptional = attributes.getInteger("id");

        if (!attributeIdOptional.isPresent()) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(Content.fromEntity(
                Employee.builder()
                        .id(attributeIdOptional.get())
                        .jobInfo(EmployeeJob.builder()
                                .company("Microsoft Corporation")
                                .website("https://www.microsoft.com/")
                                .profession("Developer C#")
                                .salary(3500)
                                .build())
                        .firstName("Piter")
                        .lastName("Harrison")
                        .build()));
    }

    @HttpAsync
    @HttpPost("/employee")
    public HttpResponse doPost(HttpRequest request) {
        System.out.println(request);
        if (!request.getAttributes().getProperties().isEmpty()) {
            return HttpResponse.badRequest();
        }

        Content content = request.getContent();
        if (content.isEmpty()) {
            return HttpResponse.noContent();
        }

        Employee employee = content.toEntity(Employee.class);
        System.out.println("POST " + employee);

        return HttpResponse.ok();
    }
}
