package com.jrest.http.client.test.socket;

import com.jrest.mvc.model.util.InputStreamUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SocketHttpClient {

    public static void main(String[] args) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/getuser?name=itzstonlex&age=20").openConnection();

        connection.setDoOutput(true);
        connection.getOutputStream().write("{\"message\": \"Привет из Казахстана!\"}".getBytes());
        connection.getOutputStream().flush();

        InputStream inputStream = connection.getInputStream();
        System.out.println(InputStreamUtil.toString(inputStream));
    }

}
