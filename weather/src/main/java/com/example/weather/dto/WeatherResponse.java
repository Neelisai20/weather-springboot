
        package com.example.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String name;
    private Main main;
    private Weather[] weather;
    private Wind wind;
    private Sys sys;

    // Getters and Setters
    public static class Main {
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        private int humidity;
        private int pressure;

        // Getters and Setters
    }

    public static class Weather {
        private String main;
        private String description;
        private String icon;

        // Getters and Setters
    }

    public static class Wind {
        private double speed;
        private int deg;

        // Getters and Setters
    }

    public static class Sys {
        private String country;
        private long sunrise;
        private long sunset;

        // Getters and Setters
    }

    // Getters and Setters for outer class
}