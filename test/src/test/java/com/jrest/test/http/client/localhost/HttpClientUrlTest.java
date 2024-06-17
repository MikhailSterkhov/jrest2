package com.jrest.test.http.client.localhost;

import com.jrest.http.client.HttpClient;
import com.jrest.http.client.HttpClients;

public class HttpClientUrlTest {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createClient();

        httpClient.executeGet("http://localhost:8080/employee?id=567")
                .ifPresent(httpResponse -> {

                    System.out.println(httpResponse.getProtocol());
                    //  HTTP/1.1
                    System.out.println(httpResponse.getHeaders().getFirst(null));
                    // HTTP/1.1 200 OK
                    System.out.println(httpResponse.getCode());
                    //  200 OK

                    System.out.println(httpResponse.getContent().getText());
                    // {"id":567,"firstName":"Piter","lastName":"Harrison","jobInfo":{"company":"Microsoft Corporation","website":"https://www.microsoft.com/","profession":"Developer C#","salary":3500}}
                });
    }
}
