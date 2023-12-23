package org.example.spring.wspapi.domain;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface WhatsappApi {
    HttpURLConnection createConnection(URL url, String token) throws IOException;
    HttpURLConnection sendMessage(HttpURLConnection connection, String template) throws IOException;
    void closeConnection(HttpURLConnection connection) throws IOException;
}
