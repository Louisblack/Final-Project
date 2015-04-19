package com.louishoughton.irrigator.forecast;

public interface LocationFinder {

    Location getLocation() throws LocationException;
}
