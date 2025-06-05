package com.example.weather.util;

import com.example.weather.dto.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    private final RestTemplate restTemplate;

    public ApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeatherData(String url) {
        logger.debug("Making API call to: {}", url);
        try {
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
            return response.getBody();
        } catch (Exception e) {
            logger.error("API call failed for URL: {}. Error: {}", url, e.getMessage());
            throw e;
        }
    }
}