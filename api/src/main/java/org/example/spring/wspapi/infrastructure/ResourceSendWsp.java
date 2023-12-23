package org.example.spring.wspapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.wspapi.domain.EventSendWSP;
import org.example.spring.wspapi.domain.WspStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class ResourceSendWsp {
    @Autowired
    private EventSendWSP handler;

    @PostMapping("/api/sendwsp/")
    public HttpEntity<Boolean> sendWsp(@RequestBody WspStructure wspStructure) throws IOException {
        log.info("Structure: "+wspStructure.toString());
        handler.handleApi(wspStructure);

        return ResponseEntity.ok(true);
    }
}
