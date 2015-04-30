package com.louishoughton.irrigator.forecast;


public class TodaysWeather {

    public static final int MINIMUM_CHANCE_OF_RAIN = 50;
    public static final double HEAVY_RAIN = 0.4;
    public static final double MODERATE_RAIN = 0.1;
    public static final double LIGHT_RAIN = 0.017;
    public static final int WATER_MULTIPLIER = 3;

    private Forecast forecast;

    public TodaysWeather(Forecast forecast) {
        this.forecast = forecast;
    }

    public boolean shouldIWater() {
        return chanceOfRainBelowMinimum() || onlyLightRain();
    }

    public int howLongShouldIWater() {
        if (shouldIWater()) {
            return Math.round(forecast.getMaximumTemperature() * WATER_MULTIPLIER);
        } else {
            return 0;
        }
    }

    private boolean onlyLightRain() {
        return chanceOfRainAboveMinimum() && forecast.getInchesPerHour() < MODERATE_RAIN;
    }

    private boolean chanceOfRainAboveMinimum() {
        return forecast.getChanceOfRainPercentage() >= MINIMUM_CHANCE_OF_RAIN;
    }

    private boolean chanceOfRainBelowMinimum() {
        return forecast.getChanceOfRainPercentage() < MINIMUM_CHANCE_OF_RAIN;
    }

}
