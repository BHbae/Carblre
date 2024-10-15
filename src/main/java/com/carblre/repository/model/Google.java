package com.carblre.repository.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.client")
public class Google {

    private String id;
    private String secret;
    private String redirectUri;

    // Getters and Setters
}