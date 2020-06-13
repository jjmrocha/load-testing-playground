package net.uiqui.load.settings.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${application.base-error-type}")
    private String baseErrorType;

    public String getBaseErrorType() {
        return baseErrorType;
    }
}
