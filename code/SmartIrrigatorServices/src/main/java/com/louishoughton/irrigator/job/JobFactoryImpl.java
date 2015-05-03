package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.forecast.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;

@Component
public class JobFactoryImpl implements JobFactory {

    private IrrigationRequestDispatcher irrigationRequestDispatcher;
    private WeatherService weatherService;

    @Autowired
    public JobFactoryImpl(IrrigationRequestDispatcher irrigationRequestDispatcher, WeatherService weatherService) {
        this.irrigationRequestDispatcher = irrigationRequestDispatcher;
        this.weatherService = weatherService;
    }

    @Override
    public IrrigationJob newJob() {
        return new IrrigationJob(weatherService, irrigationRequestDispatcher);
    }
}
