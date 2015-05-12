package com.louishoughton.irrigator.forecast;

import org.junit.Test;

import static com.louishoughton.irrigator.forecast.TodaysWeather.LIGHT_RAIN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TodaysWeatherTest {

    @Test
    public void should_tell_me_to_water_if_no_rain_predicted() throws Exception {
        double chanceOfRain = 0;
        double inchesPerHour = 0;
        float maxTemp = 0;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(0));
        assertTrue(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_not_to_water_of_heavy_rain_predicted() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN;
        double inchesPerHour = TodaysWeather.HEAVY_RAIN;
        float maxTemp = 0;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(0));
        assertFalse(todaysWeather.shouldIWater());
    }

    @Test
    public void should_not_tell_me_to_water_if_rained_recently() throws Exception {
        TodaysWeather todaysWeather =
                new TodaysWeather(new Forecast(0, 0, 0), new History(TodaysWeather.MODERATE_RAIN));
        assertFalse(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_to_water_if_only_light_rain_predicted_lightly_rained_recently()
            throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = LIGHT_RAIN;
        float maxTemp = 0;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(LIGHT_RAIN));
        assertTrue(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_to_water_if_only_light_rain_predicted() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = LIGHT_RAIN;
        float maxTemp = 0;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(0));
        assertTrue(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_not_to_water_if_moderate_rain_predicted() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = TodaysWeather.MODERATE_RAIN;
        float maxTemp = 0;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(0));
        assertFalse(todaysWeather.shouldIWater());
    }

    @Test
    public void should_be_0_if_I_should_not_water_no_matter_the_temp() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = TodaysWeather.MODERATE_RAIN;
        float maxTemp = 20;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(0));
        assertThat(todaysWeather.howLongShouldIWater(), equalTo(0));
    }

    @Test
    public void should_use_the_water_multiplier_to_work_out_how_long() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = LIGHT_RAIN;
        float maxTemp = 20;
        Forecast forecast = new Forecast(chanceOfRain, inchesPerHour, maxTemp);

        TodaysWeather todaysWeather = new TodaysWeather(forecast, new History(0));

        assertThat(todaysWeather.howLongShouldIWater(), equalTo(60));
    }
}
