package com.louishoughton.irrigator.forecast;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoogleMapsLocationFinder implements LocationFinder {

    private static final Logger LOG = LogManager.getLogger(GoogleMapsLocationFinder.class);
    
    private GeoApiContext context;
    private String address;
    private GoogleGeoCodingConnector geocodingConnector;
    
    
    
    public GoogleMapsLocationFinder(GeoApiContext context, String address) {
        super();
        this.context = context;
        this.address = address;
        this.geocodingConnector = new GoogleGeoCodingConnector();
    }

    public GoogleMapsLocationFinder(GeoApiContext context, String address, 
            GoogleGeoCodingConnector geocodingConnector) {
        super();
        this.context = context;
        this.address = address;
        this.geocodingConnector = geocodingConnector;
    }

    public Location getLocation() throws LocationException {
        LatLng latlng = findLatLong();
        return new Location(latlng.lat, latlng.lng);
    }

    private LatLng findLatLong() throws LocationException {
        GeocodingResult geocodingResult = findGeocodingResultForAddress();
        return geocodingResult.geometry.location;
    }
    
    private GeocodingResult findGeocodingResultForAddress() 
            throws LocationException {
        try {
            GeocodingResult[] results = 
                    geocodingConnector.getListOfResults(context, address);
            return selectLocationFromResults(results); 
        } catch (Exception e) {
            LOG.error("Could not find location using Google Maps", e);
            throw new LocationException(e);
        }
    }

    private GeocodingResult selectLocationFromResults(
            GeocodingResult[] results) throws LocationException {
        if (results.length == 1) {
            return results[0];
        } else {
            throw new LocationException("Multiple addresses found for this postcode");
        }
    }
    
    public static class GoogleGeoCodingConnector {
        
        public GeocodingResult[] getListOfResults(GeoApiContext context, String address) 
                throws Exception {
            return GeocodingApi.geocode(context, address).await();
        }
        
    }
    
}
