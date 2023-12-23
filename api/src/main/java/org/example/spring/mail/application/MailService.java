package org.example.spring.mail.application;

import lombok.AllArgsConstructor;
import org.example.spring.mail.domain.MailSender;
import org.example.spring.mail.domain.IMailService;
import org.example.spring.mail.domain.MailStructure;
import org.example.spring.mail.domain.TemplateStrategy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class MailService implements IMailService {
    private final MailSender sender;

    @Override
    public void sendEmail(MailStructure mail, TemplateStrategy strategy) {
        this.sendEmail(mail, strategy, "", null);
    }

    @Override
    public void sendEmail(MailStructure mail, TemplateStrategy strategy, String... values){
        this.sendEmail(mail, strategy, values, null);
    }

    @Override
    public void sendEmail(MailStructure mail, TemplateStrategy strategy, File... attachments) {
        String[] values = new String[1];
        this.sendEmail(mail, strategy, values, attachments);
    }

    @Override
    public void sendEmail(MailStructure mail, TemplateStrategy strategy, String[] values, File... attachments) {
        this.sender.setTemplateStrategy(strategy);
        this.sender.send(mail.to(), mail.subject(), strategy.buildTemplate(values), attachments);
    }
}
