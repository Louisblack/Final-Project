package com.louishoughton.irrigator.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dvdme.ForecastIOLib.FIODataPoint;
import com.github.dvdme.ForecastIOLib.FIOHourly;
import com.github.dvdme.ForecastIOLib.ForecastIO;



@Component
public class ForecastIoWeatherService implements WeatherService {

    /**
     * To be injected
     */
    private ForecastIO forecastIo;
    private LocationFinder locationFinder;
    
    public static final int HOURS_TO_LOOK_AHEAD = 3;
    
    @Autowired
    public ForecastIoWeatherService(ForecastIO forecastIo,
            LocationFinder locationFinder) {
        super();
        this.forecastIo = forecastIo;
        this.locationFinder = locationFinder;
    }

    @Override
    public Forecast getForecast() throws LocationException, ForecastException {
        FIOHourly hourly = getHourly();
        return getForecast(hourly);
    }

    private Forecast getForecast(FIOHourly hourly) {
        FIODataPoint dataPoint = getDataPointWithHighestProbabilityOfRain(hourly);
        return new Forecast(dataPoint.precipProbability() * 100, dataPoint.precipIntensity());
    }

    private FIODataPoint getDataPointWithHighestProbabilityOfRain(FIOHourly hourly) {
        FIODataPoint highest = hourly.getHour(0);
        for (int i = 1; i < HOURS_TO_LOOK_AHEAD; i++) {
            if (hourly.getHour(i).precipProbability() > highest.precipProbability()) {
                highest = hourly.getHour(i);
            }
        }
        return highest;
    }

    private FIOHourly getHourly() throws LocationException, ForecastException {
        Location location = locationFinder.getLocation();
        forecastIo.getForecast(location.getLatitude() + "", location.getLongitude() + "");
        return new FIOHourly(forecastIo);
       
    }
    
}
