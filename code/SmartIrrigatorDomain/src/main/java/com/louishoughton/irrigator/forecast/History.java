package com.louishoughton.irrigator.forecast;

public class History {

    private double highestInchesPerHour;


    public History(double highestInchesPerHour) {
        this.highestInchesPerHour = highestInchesPerHour;
    }

    public double getHighestInchesPerHour() {
        return highestInchesPerHour;
    }
}
