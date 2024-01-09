package com.example.cors.properties;

import com.example.cors.properties.allowed.CorsAllowedProperties;
import com.example.cors.properties.types.LogLevel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "app.cors")
@ConfigurationPropertiesBinding
public record CorsProperties(
        @NestedConfigurationProperty
        CorsAllowedProperties allowed,
        String[] exposedHeaders,
        Boolean allowsCredentials,
        Long maxAge,
        LogLevel logLevel
) {
    public CorsProperties {
        if (allowed == null) {
            allowed = CorsAllowedProperties.defaultInstance();
        }

        if (exposedHeaders == null || exposedHeaders.length == 0) {
            exposedHeaders = new String[] {"*"};
        }

        if (allowsCredentials == null) {
            allowsCredentials = true;
        }

        if (maxAge == null) {
            maxAge = 1800L;
        }

        if (logLevel == null) {
            logLevel = LogLevel.INFO;
        }
    }
}