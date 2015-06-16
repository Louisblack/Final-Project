package com.louishoughton.irrigator.forecast;

import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.FIODataPoint;
import com.github.dvdme.ForecastIOLib.FIOHourly;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        updateForecast();
        FIOHourly hourly = getHourly();
        FIODaily daily = getDaily();
        return getForecast(hourly, daily);
    }


    private Forecast getForecast(FIOHourly hourly, FIODaily daily) {
        FIODataPoint today = daily.getDay(0);
        FIODataPoint rainiestHour = getDataPointWithHighestProbabilityOfRain(hourly);

        return new Forecast(rainiestHour.precipProbability() * 100,
                rainiestHour.precipIntensity(), today.temperatureMax().floatValue());
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
        return new FIOHourly(forecastIo);
    }

    private FIODaily getDaily() {
        return new FIODaily(forecastIo);
    }

    private boolean updateForecast() {
        return forecastIo.getForecast(this.location.getLatitude() + "", 
                this.location.getLongitude() + "");
    }
}
