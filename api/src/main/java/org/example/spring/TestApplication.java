package org.example.spring;

import org.example.spring.mail.domain.MailStructure;
import org.example.spring.mail.infrastructure.SenderMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TestApplication {
    @Autowired
    private static SenderMail testSendMail;
    public static void main(String... args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
