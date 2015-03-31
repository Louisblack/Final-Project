package com.louishoughton.irrigator.error;

public class LocationException extends Exception {


    private static final long serialVersionUID = -6758061290088720196L;

    public LocationException(Exception exception) {
        super(exception);
    }
}
