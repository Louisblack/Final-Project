package com.louishoughton.irrigator.forecast;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Immutable
public class Forecast {

    @Id
    @GeneratedValue
    private int id;
    private double chanceOfRainPercentage;
    private double inchesPerHour;
    private float maximumTemperature;

    public Forecast(double chanceOfRain, double inchesPerHour, float maximumTemperature) {
        super();
        this.chanceOfRainPercentage = chanceOfRain;
        this.inchesPerHour = inchesPerHour;
        this.maximumTemperature = maximumTemperature;
    }

    public Forecast() {
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setChanceOfRainPercentage(double chanceOfRainPercentage) {
        this.chanceOfRainPercentage = chanceOfRainPercentage;
    }

    public void setInchesPerHour(double inchesPerHour) {
        this.inchesPerHour = inchesPerHour;
    }

    public void setMaximumTemperature(float maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
