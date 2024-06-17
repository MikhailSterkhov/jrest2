package com.jrest.test.http.client.https;

import com.jrest.http.client.HttpClient;
import com.jrest.http.client.HttpClients;

public class HttpClientUrlTest {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createClient();

        httpClient.executeGet("https://catfact.ninja/fact")
                .ifPresent(httpResponse -> {

                    System.out.println(httpResponse.getProtocol());
                    System.out.println(httpResponse.getHeaders().getFirst(null));
                    //  HTTP/1.1 200 OK
                    System.out.println(httpResponse.getCode());
                    //  200 OK

                    System.out.println(httpResponse.getContent().getText());
                    // {"fact":"A cat usually has about 12 whiskers on each side of its face.","length":61}
                });
    }
}
