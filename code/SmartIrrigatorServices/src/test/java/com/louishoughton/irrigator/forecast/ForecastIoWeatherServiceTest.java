package com.louishoughton.irrigator.forecast;

import com.louishoughton.irrigator.context.ApisContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApisContext.class})
public class ForecastIoWeatherServiceTest {

    @Autowired
    private ForecastIoWeatherService service;
    
    @Test
    @Ignore
    public void test() throws LocationException, ForecastException {
        TodaysWeather forecast = service.getTodaysWeather();
        System.out.print(forecast);
    }

}
