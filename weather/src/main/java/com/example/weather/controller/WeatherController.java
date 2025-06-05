package com.example.weather.controller;

import com.example.weather.dto.WeatherResponse;
import com.example.weather.exception.WeatherServiceException;
import com.example.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String city) {
        logger.info("Received request for weather data for city: {}", city);
        try {
            WeatherResponse response = weatherService.getWeather(city);
            logger.debug("Successfully fetched weather data for city: {}", city);
            return ResponseEntity.ok(response);
        } catch (WeatherServiceException e) {
            logger.error("Error fetching weather data for city: {}. Error: {}", city, e.getMessage());
            throw e;
        }
    }
}