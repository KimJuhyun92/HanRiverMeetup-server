package com.hangang.HangangRiver.weather.dao;

import com.hangang.HangangRiver.weather.model.Weather;

public interface WeatherMapper {
	int insert(Weather weather);
	Weather selectWeather();
}
