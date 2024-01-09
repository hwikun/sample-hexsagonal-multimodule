package com.example.healthcheck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public final class HealthCheckApi {

    private final Environment environment;

    @GetMapping("/actuator/health")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        log.info(String.format(
                "Health Check OK\n\n[Info]\n\nServer Name: %s\nServer Profiles: %s",
                environment.getProperty("spring.application.name"),
                List.of(environment.getActiveProfiles())
        ));
        return "I'm fine!";
    }
}
