package org.example.spring.wspapi.domain;

import lombok.*;

import java.net.MalformedURLException;
import java.net.URL;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class WspStructure {
    private String cellNumber;
    private String numberId;
    public String token;

    public String generateSimpleTemplate(){
        var builder = new StringBuilder();
        builder.append("{ ")
                .append("\"messaging_product\": \"whatsapp\", ")
                .append("\"to\": \"").append(this.getCellNumber()).append("\", ")
                .append("\"type\": \"template\", ").append("\"template\": ")
                .append("  { \"name\": \"hello_world\", ")
                .append("    \"language\": { \"code\": \"en_US\" } ")
                .append("  } ").append("}");

        return builder.toString();
    }
    public String generateOwnTemplate(){
        var builder = new StringBuilder();
        builder.append("{ ")
                .append("\"messaging_product\": \"whatsapp\", ")
                .append("\"to\": \"").append(this.getCellNumber()).append("\", ")
                .append("\"type\": \"template\", ").append("\"template\": ")
                .append("  { \"name\": \"my_template2\", ")
                .append("    \"language\": { \"code\": \"es_ARG\" } ")
                .append("  } ").append("}");

        return builder.toString();
    }

    public URL createURL() throws MalformedURLException {
        return new URL("https://graph.facebook.com/v15.0/" + this.getNumberId() + "/messages");
    }


}
