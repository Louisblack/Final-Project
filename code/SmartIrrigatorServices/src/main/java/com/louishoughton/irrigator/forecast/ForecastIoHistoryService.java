package com.louishoughton.irrigator.forecast;

import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Component
public class ForecastIoHistoryService {

    public static final int MINUTES_IN_8_HOURS = 480;

    private static final Logger LOG = LogManager.getLogger(ForecastIoHistoryService.class);

    private ForecastIO forecastIo;
    private LocationFinder locationFinder;
    private Location location;

    public List<Integer> minutesToLookBack = new ArrayList<>();

    @Autowired
    public ForecastIoHistoryService(ForecastIO forecastIo, LocationFinder locationFinder) {
        this.forecastIo = forecastIo;
        this.locationFinder = locationFinder;
    }


    @PostConstruct
    public void init() throws LocationException {
        setupMinutes();
        findLocation();
    }

    public void setupMinutes() {
        for (int i = 15; i <= MINUTES_IN_8_HOURS; i += 15) {
            minutesToLookBack.add(i);
        }
    }

    public void findLocation() throws LocationException {
        this.location = locationFinder.getLocation();
    }

    public History getHistory() {
        double highestInchesPerHour = minutesToLookBack
                .stream()
                .mapToDouble(this::getPrecipitationIntensity)
                .reduce(0, (a, b) -> b > a ? b : a);
        return new History(highestInchesPerHour);
    }

    public Double getPrecipitationIntensity(Integer minutes) {
        Instant instant = Instant.now().minusSeconds(minutes * 60);
        String time = instant.getEpochSecond() + "";
        forecastIo.setTime(time + "");
        forecastIo.getForecast(location.getLatitude() + "", location.getLongitude() + "");
        FIOCurrently fioCurrently = new FIOCurrently(forecastIo);

        Double precipIntensity = fioCurrently.get().precipIntensity();
        LOG.info(instant + " - " + precipIntensity);
        return precipIntensity;
    }
}
