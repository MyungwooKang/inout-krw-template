package com.example.inoutkrwtemplate.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@Data
public class TestProperties {
    @Value("${config.test.wiremock.port:9561}")
    private int wireMockPort;
}

