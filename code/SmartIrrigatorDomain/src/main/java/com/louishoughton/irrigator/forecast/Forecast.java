package com.louishoughton.irrigator.forecast;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Forecast {

    private double chanceOfRainPercentage;
    private double inchesPerHour;
    private float maximumTemperature;

    public Forecast(double chanceOfRain, double inchesPerHour, float maximumTemperature) {
        super();
        this.chanceOfRainPercentage = chanceOfRain;
        this.inchesPerHour = inchesPerHour;
        this.maximumTemperature = maximumTemperature;
    }

    public double getChanceOfRainPercentage() {
        return chanceOfRainPercentage;
    }

    public double getInchesPerHour() {
        return inchesPerHour;
    }

    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
