package com.carblre.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    public static String apiKey;
    public static String apiSecret;
    public static String apiNumber;

    @Value("${spring.sms.api.key}")
    public void setApiKey(String apiKey) {
        AppConfig.apiKey = apiKey;
    }

    @Value("${spring.sms.api.secret}")
    public void setApiSecret(String apiSecret) {
        AppConfig.apiSecret = apiSecret;
    }

    @Value("${spring.sms.api.number}")
    public void setNumber(String apiNumber) {
        AppConfig.apiNumber = apiNumber;
    }


}