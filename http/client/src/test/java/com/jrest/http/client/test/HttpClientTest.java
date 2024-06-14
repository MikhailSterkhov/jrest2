package com.jrest.http.client.test;

import com.jrest.http.client.HttpClient;
import com.jrest.http.client.HttpClients;

public class HttpClientTest {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createClient();

        httpClient.executeOptions("https://catfact.ninja/fact")
                .ifPresent(httpResponse -> {

                    System.out.println(httpResponse.getHeaders().getFirst(null));
                    //  HTTP/1.1 200 OK
                    System.out.println(httpResponse.getCode());
                    //  200 OK

                    System.out.println(httpResponse.getContent().getHyperText());
                    // {"fact":"A cat usually has about 12 whiskers on each side of its face.","length":61}
                });
    }
}
