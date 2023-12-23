package org.example.spring.mail.domain;

/**
 * Interface to apply the strategy pattern
 */
public interface TemplateStrategy {

    String getTemplate();

    String buildTemplate(Object... values);
}
