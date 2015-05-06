package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.forecast.LocationException;
import com.louishoughton.irrigator.forecast.TodaysWeather;
import com.louishoughton.irrigator.forecast.WeatherService;
import com.louishoughton.irrigator.web.Error;
import com.louishoughton.irrigator.web.IrrigationRequest;
import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;
import com.louishoughton.irrigator.web.IrrigationResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.louishoughton.irrigator.forecast.TodaysWeather.LIGHT_RAIN;
import static com.louishoughton.irrigator.forecast.TodaysWeather.MINIMUM_CHANCE_OF_RAIN;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
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
    @Mock
    private ExecutionDao executionDao;

    private IrrigationJob job;

    @Before
    public void setUp() throws Exception {
        when(irrigationRequestDispatcher.dispatch(any(IrrigationRequest.class))).
                thenReturn(new IrrigationResponse());
        job = new IrrigationJob(weatherService, irrigationRequestDispatcher,
                executionDao);
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

    @Test
    public void should_save_error_if_eception_thrown() throws Exception {
        String message = "Oops";
        doThrow(new LocationException(message)).when(weatherService).getTodaysWeather();
        job.run();
        verify(executionDao).save(new Execution(new Error(message)));
    }


    @Test
    public void should_save_request_response_and_forecast() throws Exception {
        int howLongToWater = 40;
        IrrigationRequest request = new IrrigationRequest(howLongToWater);
        IrrigationResponse irrigationResponse = new IrrigationResponse();
        Forecast forecast = new Forecast(0.1, 0.1, 20);

        TodaysWeather todaysWeather = mock(TodaysWeather.class);
        when(todaysWeather.shouldIWater()).thenReturn(true);
        when(todaysWeather.howLongShouldIWater()).thenReturn(howLongToWater);
        when(todaysWeather.getForecast()).thenReturn(forecast);
        when(weatherService.getTodaysWeather()).thenReturn(todaysWeather);
        when(irrigationRequestDispatcher.dispatch(request)).
                thenReturn(irrigationResponse);

        job.run();

        verify(executionDao).save(new Execution(forecast, request, irrigationResponse.getErrors()));

    }
}
