package org.example.spring.mail.infrastructure;

import org.example.spring.mail.application.strategies.HelloWorldStrategy;
import org.example.spring.mail.domain.IMailService;
import org.example.spring.mail.domain.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderMail {
    @Autowired
    private IMailService service;

    @PostMapping("/api/sendmail/")
    public ResponseEntity sendMail(MailStructure mail){
        service.sendEmail(
                MailStructure.builder().subject("Subject email").to("emailReceiver@gmail.com").build(),
                new HelloWorldStrategy()
        );

        return ResponseEntity.ok(true);
    }
}
