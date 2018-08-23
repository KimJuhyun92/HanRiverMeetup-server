package com.hangang.HangangRiver.weather.service;


import com.hangang.HangangRiver.weather.dao.WeatherMapper;
import com.hangang.HangangRiver.weather.model.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {
    @Autowired
    public WeatherMapper weatherMapper;

    public Weather createWeatherInformation(Weather weather) {
    	weatherMapper.insert(weather);
    	return weather;
    }

    public Weather selectWeather(){
        return weatherMapper.selectWeather();
    }

}
