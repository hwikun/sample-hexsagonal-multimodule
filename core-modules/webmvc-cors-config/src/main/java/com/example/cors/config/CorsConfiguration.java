package com.example.cors.config;

import com.example.cors.properties.CorsProperties;
import com.example.cors.properties.types.LogLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@ConfigurationPropertiesScan(basePackageClasses = CorsProperties.class)
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfiguration implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(corsProperties.allowsCredentials())
                .exposedHeaders(corsProperties.exposedHeaders())
                .allowedHeaders(corsProperties.allowed().headers())
                .allowedMethods(corsProperties.allowed().methods())
                .allowedOrigins(corsProperties.allowed().origins())
                .maxAge(corsProperties.maxAge());

        log();
    }

    private void log() {
        if (corsProperties.logLevel() == LogLevel.OFF) {
            return;
        }

        StringBuilder exposedHeaders = new StringBuilder();
        StringBuilder allowedHeaders = new StringBuilder();
        StringBuilder allowedMethods = new StringBuilder();
        StringBuilder allowedOrigins = new StringBuilder();

        for (var item: corsProperties.exposedHeaders()) {
            exposedHeaders.append("- ");
            exposedHeaders.append(item);
            exposedHeaders.append('\n');
        }
        for (var item: corsProperties.allowed().headers()) {
            allowedHeaders.append("- ");
            allowedHeaders.append(item);
            allowedHeaders.append('\n');
        }
        for (var item: corsProperties.allowed().methods()) {
            allowedMethods.append("- ");
            allowedMethods.append(item);
            allowedMethods.append('\n');
        }
        for (var item: corsProperties.allowed().origins()) {
            allowedOrigins.append("- ");
            allowedOrigins.append(item);
            allowedOrigins.append('\n');
        }

        corsProperties.logLevel().log("""
                CORS 설정
                
                Allows Credentials: {}
                
                Exposed Headers:
                {}
                
                Allowed Headers:
                {}
                
                Allowed Methods:
                {}
                
                Allowed Origins:
                {}
                
                Max Age(at Browser): {}
                """,
                corsProperties.allowsCredentials(),
                exposedHeaders,
                allowedHeaders,
                allowedMethods,
                allowedOrigins,
                corsProperties.maxAge());
    }
}