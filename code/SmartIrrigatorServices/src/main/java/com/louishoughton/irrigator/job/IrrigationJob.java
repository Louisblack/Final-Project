package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.forecast.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.louishoughton.irrigator.valve.NodeConnectionException;
import com.louishoughton.irrigator.web.IrrigationRequest;
import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;

import java.util.Optional;

import static java.util.Optional.*;

public class IrrigationJob implements Runnable {

    private IrrigationRequestDispatcher requestDispatcher;
    private WeatherService weatherService;

    private static final Logger LOG = LogManager.getLogger(IrrigationJob.class);
    
    public static final String REQUEST_DISPATCH_ERROR = "Cannot dispatch request to watering node"; 
    
    public IrrigationJob(WeatherService weatherService, IrrigationRequestDispatcher requestDispatcher) {
        this.weatherService = weatherService;
        this.requestDispatcher = requestDispatcher;
    }

    @Override
    public void run() {
        try {
            Optional<IrrigationRequest> request = getIrrigationRequestFromForecast();
            if (request.isPresent()){
                requestDispatcher.dispatch(request.get());
            }
        } catch(Exception e) {
            LOG.error(e);
        }
    }

    private Optional<IrrigationRequest> getIrrigationRequestFromForecast()
            throws LocationException, ForecastException {

        TodaysWeather todaysWeather = weatherService.getTodaysWeather();
        if (todaysWeather.shouldIWater()) {
            return of(new IrrigationRequest(todaysWeather.howLongShouldIWater()));
        } else {
            return empty();
        }
    }
}
