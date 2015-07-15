package com.louishoughton.irrigator.web;


import com.louishoughton.irrigator.job.Execution;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExecutionDetailItem {

    private String time;
    private boolean didIrrigate;
    private int duration;
    private String pastIntensity;
    private double forecastProbability;
    private String forecastIntensity;
    private double forecastTemperature;
    private List<String> errors;

    private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");

    public ExecutionDetailItem(Execution execution) {
        this.time = formatter.format(execution.getDateRun());
        this.didIrrigate = execution.getIrrigationDuration() > 0;
        this.duration = execution.getIrrigationDuration();
        this.pastIntensity = new RainIntensity(execution.getHistoricalHighestInchesPerHour()).getFriendlyLabel();
        this.forecastProbability = execution.getForecastChanceOfRainPercentage();
        this.forecastIntensity = new RainIntensity(execution.getForecastInchesPerHour()).getFriendlyLabel();
        this.forecastTemperature = execution.getForecastMaximumTemperature();
        this.errors = execution.getErrorMessages();
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

    public String getPastIntensity() {
        return pastIntensity;
    }

    public double getForecastProbability() {
        return forecastProbability;
    }

    public String getForecastIntensity() {
        return forecastIntensity;
    }

    public double getForecastTemperature() {
        return forecastTemperature;
    }

    public List<String> getErrors() {
        return errors;
    }
}
