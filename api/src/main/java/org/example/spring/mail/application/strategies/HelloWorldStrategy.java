package org.example.spring.mail.application.strategies;

import org.example.spring.mail.domain.TemplateStrategy;
import org.springframework.stereotype.Component;

public final class HelloWorldStrategy implements TemplateStrategy {
    @Override
    public String getTemplate() {
        return """
               <html>
                   <body> 
                        <h1>Helllo world</h1>
                   </body>
               </html>
                """;
    }

    @Override
    public String buildTemplate(Object... values) {
        return String.format(buildTemplate(), values);
    }
}
