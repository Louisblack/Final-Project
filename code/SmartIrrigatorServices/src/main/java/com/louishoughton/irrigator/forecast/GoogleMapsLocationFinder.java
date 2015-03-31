package com.louishoughton.irrigator.forecast;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.louishoughton.irrigator.error.LocationException;

public class GoogleMapsLocationFinder {

    private static final Logger LOG = LogManager.getLogger(GoogleMapsLocationFinder.class);
    
    //TODO create as bean
    private GeoApiContext context;
    private String address;

    
    public GoogleMapsLocationFinder(GeoApiContext context, String address) {
        super();
        this.context = context;
        this.address = address;
    }

    public Location getLocation() throws LocationException {
        
        GeocodingResult geocodingResult = findLocation();
        LatLng latlng = geocodingResult.geometry.location;
        
        return new Location(latlng.lat, latlng.lng);
    }
    
    private GeocodingResult findLocation() throws LocationException {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            return results[0]; //TODO make sure this is right
        } catch (Exception e) {
            LOG.error("Could not find location using Google Maps", e);
            throw new LocationException(e);
        }
    }
    
}
