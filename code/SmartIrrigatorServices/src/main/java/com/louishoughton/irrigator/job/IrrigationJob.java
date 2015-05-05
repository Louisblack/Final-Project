package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.forecast.*;
import com.louishoughton.irrigator.web.Error;
import com.louishoughton.irrigator.web.IrrigationResponse;
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
    private ExecutionDao executionDao;

    private static final Logger LOG = LogManager.getLogger(IrrigationJob.class);
    
    public static final String REQUEST_DISPATCH_ERROR = "Cannot dispatch request to watering node"; 
    
    public IrrigationJob(WeatherService weatherService,
                         IrrigationRequestDispatcher requestDispatcher,
                         ExecutionDao executionDao) {
        this.weatherService = weatherService;
        this.requestDispatcher = requestDispatcher;
        this.executionDao = executionDao;
    }

    @Override
    public void run() {
        try {
            TodaysWeather todaysWeather = weatherService.getTodaysWeather();
            Optional<IrrigationRequest> request = getIrrigationRequestFromForecast(todaysWeather);
            if (request.isPresent()){
                IrrigationResponse response = requestDispatcher.dispatch(request.get());
                saveExecution(todaysWeather, request.get(), response);
            } else {
                saveExecution(todaysWeather);
            }
        } catch(LocationException | ForecastException | NodeConnectionException exception) {
            LOG.error(exception);
            saveErroredExecution(exception);
        }
    }


    private Optional<IrrigationRequest> getIrrigationRequestFromForecast(TodaysWeather weather)
            throws LocationException, ForecastException {

        if (weather.shouldIWater()) {
            return of(new IrrigationRequest(weather.howLongShouldIWater()));
        } else {
            return empty();
        }
    }

    private void saveExecution(TodaysWeather todaysWeather, IrrigationRequest request,
                               IrrigationResponse response) {
        executionDao.save(new Execution(todaysWeather.getForecast(), request, response.getErrors()));
    }

    private void saveExecution(TodaysWeather todaysWeather) {
        executionDao.save(new Execution(todaysWeather.getForecast()));
    }

    private void saveErroredExecution(Exception exception) {
        executionDao.save(new Execution(new Error(exception.getMessage())));
    }
}
