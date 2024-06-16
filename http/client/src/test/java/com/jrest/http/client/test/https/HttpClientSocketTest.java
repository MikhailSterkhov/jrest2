package com.jrest.http.client.test.https;

import com.jrest.http.client.HttpClient;
import com.jrest.http.client.HttpClients;

import java.util.concurrent.Executors;

public class HttpClientSocketTest {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createSocketClient(
                Executors.newSingleThreadExecutor());

        httpClient.executeAsyncGet("https://catfact.ninja/fact")
                .thenAccept(httpResponse -> {

                    System.out.println(httpResponse.getProtocol());
                    System.out.println(httpResponse.getHeaders().getFirst(null));
                    //  HTTP/1.1
                    System.out.println(httpResponse.getCode());
                    //  200 OK

                    System.out.println(httpResponse.getContent().getHyperText());
                    // {"fact":"A cat usually has about 12 whiskers on each side of its face.","length":61}
                });
    }
}
