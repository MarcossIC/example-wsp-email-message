package org.example.spring.wspapi.application;

import lombok.AllArgsConstructor;
import org.example.spring.wspapi.domain.EventSendWSP;
import org.example.spring.wspapi.domain.WhatsappApi;
import org.example.spring.wspapi.domain.WspStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
public class EventSendingWsp implements EventSendWSP {
    @Autowired
    private WhatsappApi api;

    public void handleApi(WspStructure structure) throws IOException {
        //To avoid failures, any type of open connection is closed.
        api.closeConnection(
                //We send the message and open the necessary connection at the same time
                api.sendMessage(
                    api.createConnection(structure.createURL(), structure.getToken()),
                    structure.generateOwnTemplate() )
        );

    }
}
