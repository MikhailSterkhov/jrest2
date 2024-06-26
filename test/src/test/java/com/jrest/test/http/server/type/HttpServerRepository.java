package com.jrest.test.http.server.type;

import com.jrest.mvc.model.authentication.*;
import com.jrest.mvc.persistence.HttpAuthenticator;
import com.jrest.test.http.server.employee.Employee;
import com.jrest.test.http.server.employee.EmployeeJob;
import com.jrest.mvc.model.*;
import com.jrest.mvc.persistence.*;

import java.util.Optional;

@HttpServer
public class HttpServerRepository {

    private static final Token.UsernameAndPassword APPROVAL_TOKEN =
            Token.UsernameAndPassword.of("jrest_admin", "password");

    @HttpAuthenticator
    public ApprovalResult approveAuth(UnapprovedRequest request) {
        return request.basicAuthenticate(APPROVAL_TOKEN);
    }

    @HttpBeforeAll
    public void before(HttpRequest httpRequest) {
        Headers headers = httpRequest.getHeaders()
                .set(Headers.Def.CONTENT_TYPE, ContentType.APPLICATION_JSON)
                .set(Headers.Def.USER_AGENT, "Mikhail Sterkhov");

        httpRequest.setHeaders(headers);
    }

    public static void main(String[] args) {

    }

    @HttpNotAuthorized
    @HttpRequestMapping(path = "/redirect")
    public HttpResponse moveToGoogle(HttpRequest httpRequest) {
        return HttpResponse.movedTemporary("https://google.com/");
    }

    @HttpNotAuthorized
    @HttpGet("/employee")
    public HttpResponse doGet(HttpRequest request) {
        Attributes attributes = request.getAttributes();
        Optional<Integer> attributeIdOptional = attributes.getInteger("id");

        if (!attributeIdOptional.isPresent()) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(Content.fromEntityJson(
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

        Employee employee = content.fromJson(Employee.class);
        System.out.println("POST " + employee);

        return HttpResponse.ok();
    }
}
