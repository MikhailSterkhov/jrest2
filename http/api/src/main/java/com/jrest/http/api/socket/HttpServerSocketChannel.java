package com.jrest.http.api.socket;

import com.jrest.http.api.socket.codec.SocketCodec;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.util.InputStreamUtil;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class HttpServerSocketChannel {

    private final SocketCodec codec;
    private final HttpServerSocketConfig config;
    private final ExecutorService executorService;
    private final BiConsumer<HttpConnectedClient, HttpRequest> requestHandler;

    private ServerSocketChannel serverSocketChannel;

    public HttpServerSocketChannel(HttpServerSocketConfig config, ExecutorService executorService, BiConsumer<HttpConnectedClient, HttpRequest> requestHandler) {
        this.config = config;
        this.codec = new SocketCodec(config.getProtocol());
        this.executorService = executorService;
        this.requestHandler = requestHandler;
    }

    public void start() throws IOException {
        if (config.isSsl()) {
            startHttpsServer();
        } else {
            startHttpServer();
        }
    }

    private void startHttpServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(config.getPort()));
        serverSocketChannel.configureBlocking(false);

        executorService.submit(() -> {
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel != null) {
                    handleClient(socketChannel.socket());
                }
            }
        });
    }

    private void startHttpsServer() throws IOException {
        SSLContext sslContext = createSSLContext();
        SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
        SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(config.getPort());

        executorService.submit(() -> {
            while (true) {
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
                handleClient(sslSocket);
            }
        });
    }

    private SSLContext createSSLContext() throws FileNotFoundException {
        File keystorePathFile = new File(config.getKeystorePath());
        if (!keystorePathFile.exists()) {
            throw new FileNotFoundException(config.getKeystorePath());
        }
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(Files.newInputStream(keystorePathFile.toPath()), config.getKeystorePassword().toCharArray());
            keyManagerFactory.init(keyStore, config.getKeyPassword().toCharArray());

            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

            return sslContext;
        } catch (Exception e) {
            throw new HttpSocketException("Failed to initialize SSL context", e);
        }
    }

    private void handleClient(Socket socket) {
        executorService.submit(() -> {
            try (InputStream inputStream = socket.getInputStream()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[8192];
                int bytesRead;
                boolean headersComplete = false;
                StringBuilder headerBuilder = new StringBuilder();

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);

                    if (!headersComplete) {
                        String part = new String(buffer, 0, bytesRead);
                        headerBuilder.append(part);

                        // Check for end of headers
                        int headerEndIndex = headerBuilder.indexOf("\r\n\r\n");
                        if (headerEndIndex != -1) {
                            headersComplete = true;
                        }
                    }

                    if (headersComplete) {
                        // If we have the complete headers, break the loop if there's no more data to read
                        break;
                    }
                }

                ByteArrayInputStream responseStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                HttpRequest request = codec.decode0(responseStream);

                HttpConnectedClient clientChannel = new HttpConnectedClient(socket, codec, config);
                requestHandler.accept(clientChannel, request);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (!config.isKeepAlive()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void shutdown() {
        try {
            serverSocketChannel.close();
            executorService.shutdown();
        } catch (IOException exception) {
            throw new HttpSocketException(exception);
        }
    }
}
