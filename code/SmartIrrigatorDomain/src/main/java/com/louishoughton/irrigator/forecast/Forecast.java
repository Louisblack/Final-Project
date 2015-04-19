package com.louishoughton.irrigator.forecast;

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
    
}
