package com.jrest.binary;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HttpClientHelper {
   //private HttpClient client;
   //private String host;

   //public HttpClientHelper(ClientConfig config) {
   //    this.host = config.getHost();
   //    this.client = HttpClient.newBuilder()
   //            .connectTimeout(Duration.ofMillis(config.getTimeout()))
   //            .build();
   //}

   //public void executeRequest(RequestConfig requestConfig) throws Exception {
   //    URI uri = new URI(host + requestConfig.getUri() + buildQueryString(requestConfig.getAttributes()));
   //    HttpRequest.Builder builder = HttpRequest.newBuilder()
   //            .uri(uri)
   //            .method(requestConfig.getMethod(), buildBody(requestConfig.getBody()))
   //            .timeout(Duration.ofMillis(5000));

   //    for (Map.Entry<String, List<String>> entry : requestConfig.getHeaders().entrySet()) {
   //        for (String value : entry.getValue()) {
   //            builder.header(entry.getKey(), value);
   //        }
   //    }

   //    HttpRequest request = builder.build();
   //    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

   //    System.out.println("Response: " + response.body());
   //}

   //private String buildQueryString(Properties attributes) {
   //    if (attributes.isEmpty()) {
   //        return "";
   //    }

   //    StringBuilder queryString = new StringBuilder("?");
   //    for (String name : attributes.stringPropertyNames()) {
   //        if (queryString.length() > 1) {
   //            queryString.append("&");
   //        }
   //        queryString.append(name).append("=").append(attributes.getProperty(name));
   //    }
   //    return queryString.toString();
   //}

   //private HttpRequest.BodyPublisher buildBody(Properties body) {
   //    if (body.containsKey("content")) {
   //        return HttpRequest.BodyPublishers.ofString(body.getProperty("content"));
   //    }
   //    return HttpRequest.BodyPublishers.noBody();
   //}
}