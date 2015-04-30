package com.louishoughton.irrigator.forecast;

import com.github.dvdme.ForecastIOLib.FIODataPoint;
import com.github.dvdme.ForecastIOLib.FIOHourly;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class ForecastIoForecastService {

    private ForecastIO forecastIo;
    private LocationFinder locationFinder;
    private Location location;

    public static final int HOURS_TO_LOOK_AHEAD = 4;

    @Autowired
    public ForecastIoForecastService(ForecastIO forecastIo, LocationFinder locationFinder) {
        this.forecastIo = forecastIo;
        this.locationFinder = locationFinder;
    }

    @PostConstruct
    public void findLocation() throws LocationException {
        this.location = locationFinder.getLocation();
    }

    Forecast getForecast() throws LocationException, ForecastException {
        FIOHourly hourly = getHourly();
        return getForecast(hourly);
    }

    public History getHistory() throws LocationException, ForecastException {
        Arrays.asList(1, 2, 3).stream().forEach(hour -> forecastIo.setTime(hour+""));
        return null;
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
        forecastIo.getForecast(this.location.getLatitude() + "", this.location.getLongitude() + "");
        return new FIOHourly(forecastIo);
    }
}
