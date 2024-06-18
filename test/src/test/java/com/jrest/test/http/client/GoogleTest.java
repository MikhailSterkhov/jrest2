package com.jrest.test.http.client;

import java.net.URL;
import java.net.URLConnection;

public class GoogleTest {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://google.com");
        URLConnection conn = url.openConnection();

        conn.getHeaderFields().forEach((s, strings) -> {
            System.out.println(s + ":");
            for (String string : strings) {
                System.out.println("  -> " + string);
            }
        });
    }
}
