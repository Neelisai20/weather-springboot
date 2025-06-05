package com.example.weather.service.impl;

import com.example.weather.config.AppConfig;
import com.example.weather.dto.WeatherResponse;
import com.example.weather.exception.WeatherServiceException;
import com.example.weather.service.WeatherService;
import com.example.weather.util.ApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final ApiClient apiClient;
    private final AppConfig appConfig;

    public WeatherServiceImpl(ApiClient apiClient, AppConfig appConfig) {
        this.apiClient = apiClient;
        this.appConfig = appConfig;
    }

    @Override
    public WeatherResponse getWeather(String city) throws WeatherServiceException {
        // Track city in MDC
        MDC.put("city", city);
        logger.debug("Fetching weather data");

        try {
            String url = String.format("%s?q=%s&appid=%s&units=metric",
                    appConfig.getApiUrl(), city, appConfig.getApiKey());

            // Track downstream call
            MDC.put("downstreamService", "OpenWeatherMap");
            long startTime = System.currentTimeMillis();

            WeatherResponse response = apiClient.getWeatherData(url);

            // Record duration
            long duration = System.currentTimeMillis() - startTime;
            MDC.put("downstreamDuration", String.valueOf(duration));

            if (response == null) {
                logger.warn("Null response received");
                throw new WeatherServiceException("No weather data found");
            }

            logger.info("Successfully fetched weather data");
            return response;
        } catch (Exception e) {
            logger.error("Failed to fetch weather data");
            throw new WeatherServiceException("Failed to fetch weather data", e);
        } finally {
            // Cleanup MDC
            MDC.remove("city");
            MDC.remove("downstreamService");
            MDC.remove("downstreamDuration");
        }
    }
}


//package com.example.weather.service.impl;
//
//        import com.example.weather.config.AppConfig;
//        import com.example.weather.dto.WeatherResponse;
//        import com.example.weather.exception.WeatherServiceException;
//        import com.example.weather.service.WeatherService;
//        import com.example.weather.util.ApiClient;
//        import org.slf4j.Logger;
//        import org.slf4j.LoggerFactory;
//        import org.springframework.stereotype.Service;

//@Service
//public class WeatherServiceImpl implements WeatherService {
//    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
//
//    private final ApiClient apiClient;
//    private final AppConfig appConfig;
//
//    public WeatherServiceImpl(ApiClient apiClient, AppConfig appConfig) {
//        this.apiClient = apiClient;
//        this.appConfig = appConfig;
//    }
//
//    @Override
//    public WeatherResponse getWeather(String city) throws WeatherServiceException {
//        logger.debug("Fetching weather data for city: {}", city);
//        try {
//            String url = String.format("%s?q=%s&appid=%s&units=metric",
//                    appConfig.getApiUrl(), city, appConfig.getApiKey());
//
//            WeatherResponse response = apiClient.getWeatherData(url);
//
//            if (response == null) {
//                logger.warn("Received null response for city: {}", city);
//                throw new WeatherServiceException("No weather data found for the specified location");
//            }
//
//            return response;
//        } catch (Exception e) {
//            logger.error("Error while fetching weather data for city: {}. Error: {}", city, e.getMessage());
//            throw new WeatherServiceException("Failed to fetch weather data: " + e.getMessage(), e);
//        }
//    }
//}