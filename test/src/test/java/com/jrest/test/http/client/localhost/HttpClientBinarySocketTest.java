package com.jrest.test.http.client.localhost;

import com.jrest.http.client.BinaryHttpClient;
import com.jrest.http.client.HttpClients;
import com.jrest.mvc.model.Attributes;
import com.jrest.mvc.model.Content;
import com.jrest.test.http.server.employee.Employee;

public class HttpClientBinarySocketTest {

    public static void main(String[] args) {
        BinaryHttpClient httpClient = HttpClients.binary(
                HttpClients.createSocketClient(),
                HttpClientBinarySocketTest.class.getResourceAsStream("/employee.restbin"));

        httpClient.executeBinary("post_employee",
                        Attributes.newAttributes()
                                .with("employee", Content.fromEntity(Employee.builder()
                                                .id(1)
                                                .firstName("Mikhail")
                                                .lastName("Sterkhov")
                                                .build())
                                        .getText()))
                .ifPresent(System.out::println);

        httpClient.executeBinary("get_employee",
                        Attributes.newAttributes()
                                .with("employee_id", 1))
                .ifPresent(System.out::println);
    }
}
