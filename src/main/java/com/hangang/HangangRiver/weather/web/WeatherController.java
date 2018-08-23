package com.hangang.HangangRiver.weather.web;

import com.hangang.HangangRiver.weather.model.Weather;
import com.hangang.HangangRiver.weather.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    private ResponseEntity<Weather> getWeather(HttpServletRequest request){
        Weather weatherInformation = weatherService.selectWeather();
        return ResponseEntity.ok().body(weatherInformation);
    }

}
