package org.example.spring.wspapi.domain;

import java.io.IOException;

public interface EventSendWSP {
    void handleApi(WspStructure structure) throws IOException;
}
