package org.example.spring.mail.infrastructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.spring.mail.domain.MailSender;
import org.example.spring.mail.domain.TemplateStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

@Primary
@Service
@RequiredArgsConstructor
public class CustomSpringMailSender implements MailSender {
    private static final File[] NO_ATTACHMENTS = null;
    private final JavaMailSender sender;
    private TemplateStrategy strategy;

    /**
     * Configuracion del sender
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * Send an Email
     *
     * @param to          Email to which it is addressed
     * @param subject     Email Title/Subject
     * @param textMessage Email body template
     * @return Mail sending status
     */
    @Override
    public Boolean send(final String to, final String subject, final String textMessage) {
        return this.send(to, subject, textMessage, NO_ATTACHMENTS);
    }


    /**
     * Send an Email
     *
     * @param to          Email to which it is addressed
     * @param subject     Email Title/Subject
     * @param textMessage Email body template
     * @param attachments Files to send by email
     * @return Mail sending status
     */
    @Override
    public Boolean send(final String to, final String subject, final String textMessage, File... attachments) {
        var send = false;
        Assert.hasLength(to, () -> "Verifica que los valores no sean nulos, to is: " + to);
        Assert.hasLength(subject, () -> "Verifica que los valores no sean nulos, subject is: " + subject);
        Assert.hasLength(textMessage, () -> "Verifica que los valores no sean nulos");

        final MimeMessage message = sender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(new InternetAddress(this.from));
            helper.setText(textMessage, true);

            if (attachments != null) preparingAttachment(Arrays.stream(attachments), helper);

            this.sender.send(message);
            send = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return send;
    }

    /**
     * Cambia el Template que se utilizara
     *
     * @param strategy Estrategia a utilizar para el template
     */
    @Override
    public void setTemplateStrategy(TemplateStrategy strategy) {
        this.strategy = strategy;
    }


    private void preparingAttachment(Stream<File> attachments, MimeMessageHelper helper) {
        attachments.forEach(attachment -> {
            try {
                FileSystemResource file = new FileSystemResource(attachment);
                helper.addAttachment(attachment.getName(), file);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
