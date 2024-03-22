package com.jrest.http.client.test.socket;

import com.jrest.mvc.model.util.InputStreamUtil;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHttpServer {
    public static void main(String[] args) {
        int port = 8080;
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection accepted.");

                handleRequest(socket);
            }
        } catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) throws Exception {
        printRequest(socket);
        OutputStream outputStream = socket.getOutputStream();
        
        // Отправляем ответ
        String response = "HTTP/1.1 200 OK\r\n" +
                          "Content-Type: text/plain\r\n" +
                          "\r\n" +
                          "Hello, World!";
        outputStream.write(response.getBytes());

        socket.close();
    }

    private static void printRequest(Socket socket) throws Exception {
        String request = InputStreamUtil.toString(socket.getInputStream());

        // Выводим содержимое запроса
        System.out.println(request);
    }
}