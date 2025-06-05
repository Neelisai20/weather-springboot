package com.example.weather.service;

import com.example.weather.dto.WeatherResponse;
import com.example.weather.exception.WeatherServiceException;

public interface WeatherService {
    WeatherResponse getWeather(String city) throws WeatherServiceException;
}