package org.example.spring.mail.domain;

import lombok.*;

@Builder
public record MailStructure(String subject, String to) {
}
