package com.louishoughton.irrigator.forecast;

public class ForecastException extends Exception {

    private static final long serialVersionUID = 5152246408192431439L;

    public ForecastException(Throwable exception) {
        super(exception);
    }
    
    public ForecastException(String message) {
        super(message);
    }
}
