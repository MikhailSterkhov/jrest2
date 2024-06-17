package com.jrest.binary;

import java.io.*;
import java.util.*;

final class HttpBinaryParser {

    private Properties globalProperties;
    private int indexOfLine;

    public HttpClientProperties parseClientConfig(BufferedReader reader) {
        globalProperties = new Properties();
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                indexOfLine++;

                if (line.trim().startsWith("//"))
                    continue;
                if (line.trim().isEmpty())
                    break;

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    globalProperties.setProperty(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (Throwable ex) {
            throw new JrestBinaryException("Error on " + (indexOfLine - 1) + " line: " + line, ex);
        }

        return new HttpClientProperties(globalProperties);
    }

    public List<HttpRequestProperties> parseRequestConfigs(BufferedReader reader) {
        List<HttpRequestProperties> requests = new ArrayList<>();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("//"))
                    continue;

                line = replacePlaceholders(line);

                String[] parts = line.split(":\\s+", 2);
                String name = parts[0];
                String[] methodAndUri = parts[1].split("\\s+", 2);
                String method = methodAndUri[0];
                String uri = methodAndUri[1];

                Map<String, List<String>> headers = new HashMap<>();
                Properties attributes = new Properties();
                Properties body = new Properties();

                indexOfLine++;

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    line = replacePlaceholders(line);

                    if (line.trim().startsWith("head")) {
                        String[] headParts = line.trim().split("\\s+", 3);
                        headers.computeIfAbsent(headParts[1], k -> new ArrayList<>()).add(headParts[2].substring(2));
                    } else if (line.trim().startsWith("attr")) {
                        String[] attrParts = line.split("=", 2);
                        attributes.setProperty(attrParts[0].trim().substring(5), attrParts[1].trim());
                    } else if (line.trim().startsWith("body")) {
                        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                            if (!line.contains("=")) {
                                break;
                            }

                            line = replacePlaceholders(line);

                            String[] bodyParts = line.split("=", 2);
                            body.setProperty(bodyParts[0].trim(), bodyParts[1].trim());

                            indexOfLine++;
                        }
                    }

                    indexOfLine++;
                }

                requests.add(new HttpRequestProperties(name, method, uri.substring(0, uri.length() - 2), headers, attributes, body));
            }
        } catch (Throwable ex) {
            throw new JrestBinaryException("Error on " + indexOfLine + " line: " + line, ex);
        }

        return requests;
    }

    private String replacePlaceholders(String value) {
        String res = value;
        for (Object key : globalProperties.keySet()) {
            String placeholder = String.format("${%s}", key);
            res = res.replace(placeholder, globalProperties.getProperty(key.toString()));
        }
        return res;
    }
}