package com.louishoughton.irrigator.forecast;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Forecast {

    private double chanceOfRainPercentage;
    private double inchesPerHour;
    
    public Forecast(double chanceOfRain, double inchesPerHour) {
        super();
        this.chanceOfRainPercentage = chanceOfRain;
        this.inchesPerHour = inchesPerHour;
    }

    public double getChanceOfRainPercentage() {
        return chanceOfRainPercentage;
    }

    public double getInchesPerHour() {
        return inchesPerHour;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
