package com.louishoughton.irrigator.forecast;


public class TodaysWeather {

    public static final int MINIMUM_CHANCE_OF_RAIN = 50;
    public static final double HEAVY_RAIN = 0.4;
    public static final double MODERATE_RAIN = 0.1;
    public static final double LIGHT_RAIN = 0.017;
    public static final int WATER_MULTIPLIER = 3;

    private Forecast forecast;
    private History history;

    public TodaysWeather(Forecast forecast, History history) {
        this.forecast = forecast;
        this.history = history;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public History getHistory() {
        return history;
    }

    public boolean shouldIWater() {
        return (chanceOfRainBelowMinimum() || onlyLightRain()) && notEnoughRainRecently();
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

    private boolean notEnoughRainRecently() {
        return history.getHighestInchesPerHour() < MODERATE_RAIN;
    }
}
