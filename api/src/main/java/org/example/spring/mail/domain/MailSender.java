package org.example.spring.mail.domain;

import java.io.File;

public interface MailSender {
    Boolean send(final String to, final String subject, final String textMessage);
    Boolean send(final String to, final String subject, final String textMessage, File... attachments);

    void setTemplateStrategy(TemplateStrategy strategy);
}
