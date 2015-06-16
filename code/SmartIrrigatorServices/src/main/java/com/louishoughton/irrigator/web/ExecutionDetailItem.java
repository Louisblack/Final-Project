package com.louishoughton.irrigator.web;


import com.louishoughton.irrigator.job.Execution;

import java.text.SimpleDateFormat;

public class ExecutionDetailItem {

    private String time;
    private boolean didIrrigate;
    private int duration;
    private double pastIntensity;
    private double forecastProbability;
    private double forecastIntensity;
    private double forecastTemperature;

    private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");

    public ExecutionDetailItem(Execution execution) {
        this.time = formatter.format(execution.getDateRun());
        this.didIrrigate = execution.getIrrigationDuration() > 0;
        this.duration = execution.getIrrigationDuration();
        this.pastIntensity = execution.getHistory().getHighestInchesPerHour();
        this.forecastProbability = execution.getForecast().getChanceOfRainPercentage();
        this.forecastIntensity = execution.getForecast().getInchesPerHour();
        this.forecastTemperature = execution.getForecast().getMaximumTemperature();
    }

    public String getTime() {
        return time;
    }

    public boolean isDidIrrigate() {
        return didIrrigate;
    }

    public int getDuration() {
        return duration;
    }

    public double getPastIntensity() {
        return pastIntensity;
    }

    public double getForecastProbability() {
        return forecastProbability;
    }

    public double getForecastIntensity() {
        return forecastIntensity;
    }

    public double getForecastTemperature() {
        return forecastTemperature;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }
}
