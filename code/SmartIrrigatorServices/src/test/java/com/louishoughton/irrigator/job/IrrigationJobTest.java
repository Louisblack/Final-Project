package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.forecast.TodaysWeather;
import com.louishoughton.irrigator.forecast.WeatherService;
import com.louishoughton.irrigator.web.IrrigationRequest;
import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.louishoughton.irrigator.forecast.TodaysWeather.LIGHT_RAIN;
import static com.louishoughton.irrigator.forecast.TodaysWeather.MINIMUM_CHANCE_OF_RAIN;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IrrigationJobTest {

    @Mock
    private WeatherService weatherService;
    @Mock
    private IrrigationRequestDispatcher irrigationRequestDispatcher;

    private IrrigationJob job;

    @Before
    public void setUp() throws Exception {
        job = new IrrigationJob(weatherService, irrigationRequestDispatcher);
    }

    @Test
    public void should_query_weather_service() throws Exception {
        TodaysWeather todaysWeather =
                new TodaysWeather(new Forecast(MINIMUM_CHANCE_OF_RAIN, LIGHT_RAIN, 25));
        when(weatherService.getTodaysWeather()).thenReturn(todaysWeather);
        job.run();
        verify(weatherService).getTodaysWeather();
    }

    @Test
    public void should_send_request_to_watering_node() throws Exception {
        int howLongToWater = 40;
        TodaysWeather todaysWeather = mock(TodaysWeather.class);
        when(todaysWeather.shouldIWater()).thenReturn(true);
        when(todaysWeather.howLongShouldIWater()).thenReturn(howLongToWater);
        when(weatherService.getTodaysWeather()).thenReturn(todaysWeather);

        job.run();

        verify(irrigationRequestDispatcher).dispatch(new IrrigationRequest(howLongToWater));
    }

    @Test
    public void should_not_send_request_to_watering_node() throws Exception {
        TodaysWeather todaysWeather = mock(TodaysWeather.class);
        when(todaysWeather.shouldIWater()).thenReturn(false);
        when(weatherService.getTodaysWeather()).thenReturn(todaysWeather);

        job.run();

        verify(irrigationRequestDispatcher, never()).dispatch(new IrrigationRequest());
    }
}
