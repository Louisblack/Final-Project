package com.louishoughton.irrigator.forecast;


public interface WeatherService {

    Forecast getForecast() throws LocationException, ForecastException;
}
