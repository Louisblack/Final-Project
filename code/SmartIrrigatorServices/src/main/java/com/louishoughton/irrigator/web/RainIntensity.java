package com.louishoughton.irrigator.web;


import static com.louishoughton.irrigator.forecast.TodaysWeather.HEAVY_RAIN;
import static com.louishoughton.irrigator.forecast.TodaysWeather.LIGHT_RAIN;

public class RainIntensity {

    public static final String HEAVY_RAIN_LABEL = "heavy rain";
    public static final String MODERATE_RAIN_LABEL = "moderate rain";
    public static final String LIGHT_RAIN_LABEL = "light rain";
    public static final String NO_RAIN_LABEL = "no rain";

    private double intensity;

    public RainIntensity(double intensity) {
        this.intensity = intensity;
    }

    public String getFriendlyLabel() {
        String label = NO_RAIN_LABEL;
        if (intensity > 0 && intensity <= LIGHT_RAIN) {
            label = LIGHT_RAIN_LABEL;
        }
        if (intensity > LIGHT_RAIN && intensity < HEAVY_RAIN) {
            label = MODERATE_RAIN_LABEL;
        }
        if (intensity >= HEAVY_RAIN) {
            label = HEAVY_RAIN_LABEL;
        }
        return label;
    }
}
