package org.example.spring.mail.domain;

import java.io.File;

public interface IMailService {
    void sendEmail(MailStructure mail, TemplateStrategy strategy);
    void sendEmail(MailStructure mail, TemplateStrategy strategy, String... values);
    void sendEmail(MailStructure mail, TemplateStrategy strategy, File... attachments);

    void sendEmail(MailStructure mail, TemplateStrategy strategy, String[] values, File... attachments);
}
