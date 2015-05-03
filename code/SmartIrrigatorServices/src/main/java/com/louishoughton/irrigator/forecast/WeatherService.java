package com.louishoughton.irrigator.forecast;


public interface WeatherService {

    TodaysWeather getTodaysWeather() throws LocationException, ForecastException;
}
