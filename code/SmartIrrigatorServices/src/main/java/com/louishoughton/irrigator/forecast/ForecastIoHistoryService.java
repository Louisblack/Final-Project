package com.louishoughton.irrigator.forecast;

import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;


@Component
public class ForecastIoHistoryService {

    private ForecastIO forecastIo;
    private LocationFinder locationFinder;

    private Location location;

    public static final List<Integer> MINUTES_TO_LOOK_BACK =
            Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120);

    @Autowired
    public ForecastIoHistoryService(ForecastIO forecastIo, LocationFinder locationFinder) {
        this.forecastIo = forecastIo;
        this.locationFinder = locationFinder;
    }


    @PostConstruct
    public void findLocation() throws LocationException {
        this.location = locationFinder.getLocation();
    }

    public History getHistory() {
        MINUTES_TO_LOOK_BACK.stream().forEach(minutes -> {

            String time = Instant.now().minusSeconds(minutes * 60).getEpochSecond()+"";
            System.out.println(time);
            forecastIo.setTime(time + "");
            forecastIo.getForecast(location.getLatitude()+"", location.getLongitude()+"");
            FIOCurrently fioCurrently = new FIOCurrently(forecastIo);
            System.out.println(minutes + " ago.");
            System.out.println(fioCurrently.get().precipIntensity());
            System.out.println(fioCurrently.get().precipProbability());

            System.out.println("--------");

        });
        return null;
    }
}
