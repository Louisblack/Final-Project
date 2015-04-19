package com.louishoughton.irrigator.forecast;

public class LocationException extends Exception {


    private static final long serialVersionUID = -6758061290088720196L;

    public LocationException(Exception exception) {
        super(exception);
    }
    
    public LocationException(String message) {
        super(message);
    }
}
