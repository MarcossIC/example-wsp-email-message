package org.example.spring.wspapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.wspapi.domain.WhatsappApi;
import org.example.spring.wspapi.domain.WspStructure;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
@Slf4j
public class ConnectionToWspApi implements WhatsappApi {

    public HttpURLConnection createConnection(URL url, String token) throws IOException {
        var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json; application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        return connection;
    }

    public HttpURLConnection sendMessage(HttpURLConnection connection, String template) throws IOException {
        var writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(template);
        writer.flush();
        writer.close();
        return connection;
    }

    public void closeConnection(HttpURLConnection connection) throws IOException {
        connection.getOutputStream().close();

        InputStream responseStream = connection.getResponseCode() / 100 == 2
                ? connection.getInputStream()
                : connection.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        log.info("Response: "+response);
        connection.disconnect();
    }
}
