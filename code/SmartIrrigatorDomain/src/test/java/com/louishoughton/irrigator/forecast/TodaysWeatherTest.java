package com.louishoughton.irrigator.forecast;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodaysWeatherTest {

    @Test
    public void should_tell_me_to_water_if_no_rain_predicted() throws Exception {

        double chanceOfRain = 0;
        double inchesPerHour = 0;
        TodaysWeather todaysWeather = new TodaysWeather(new Forecast(chanceOfRain, inchesPerHour));
        assertTrue(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_not_to_water_of_heavy_rain_predicted() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN;
        double inchesPerHour = TodaysWeather.HEAVY_RAIN;

        TodaysWeather todaysWeather = new TodaysWeather(new Forecast(chanceOfRain, inchesPerHour));
        assertFalse(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_to_water_if_only_light_rain_predicted() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = TodaysWeather.LIGHT_RAIN;

        TodaysWeather todaysWeather = new TodaysWeather(new Forecast(chanceOfRain, inchesPerHour));
        assertTrue(todaysWeather.shouldIWater());
    }

    @Test
    public void should_tell_me_not_to_water_if_moderate_rain_predicted() throws Exception {
        double chanceOfRain = TodaysWeather.MINIMUM_CHANCE_OF_RAIN + 10;
        double inchesPerHour = TodaysWeather.MODERATE_RAIN;

        TodaysWeather todaysWeather = new TodaysWeather(new Forecast(chanceOfRain, inchesPerHour));
        assertFalse(todaysWeather.shouldIWater());
    }
}
