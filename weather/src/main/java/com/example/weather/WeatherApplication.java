package com.example.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}
}


//	<?xml version="1.0" encoding="UTF-8"?>
//<configuration>
//<include resource="org/springframework/boot/logging/logback/defaults.xml"/>
//
//<appender name="JSON" class="ch.qos.logback.core.ConsoleAppender">
//<encoder class="net.logstash.logback.encoder.LogstashEncoder">
//<customFields>{"app":"weather","env":"${spring.profiles.active:default}"}</customFields>
//<timeZone>UTC</timeZone>
//</encoder>
//</appender>
//
//<appender name="FILE_JSON" class="ch.qos.logback.core.FileAppender">
//<file>${LOG_FILE:-logs/weather-app.json.log}</file>
//<encoder class="net.logstash.logback.encoder.LogstashEncoder">
//<customFields>{"app":"weather","env":"${spring.profiles.active:default}"}</customFields>
//<timeZone>UTC</timeZone>
//</encoder>
//</appender>
//<root level="INFO">
//<appender-ref ref="JSON"/>
//<appender-ref ref="FILE_JSON"/>
//</root>
//</configuration>