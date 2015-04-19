package com.louishoughton.irrigator.forecast;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.louishoughton.irrigator.forecast.GoogleMapsLocationFinder.GoogleGeoCodingConnector;

@RunWith(MockitoJUnitRunner.class)
public class GoogleMapsLocationFinderTest {

    @Mock
    private GeoApiContext geoApiContext;
    @Mock
    private GoogleGeoCodingConnector geocodingConnector;
    private GoogleMapsLocationFinder locationFinder;
    
    public static final String ADDRESS = "MK13 6HG";
    
    @Before
    public void setup() {
        locationFinder = new GoogleMapsLocationFinder(geoApiContext, ADDRESS, geocodingConnector);
    }
    
    @Test
    public void should_return_the_location_provided_by_context() throws Exception {
        final double lat = 2.34567;
        final double lng = -4.76747;
        when(geocodingConnector.getListOfResults(geoApiContext, ADDRESS))
            .thenReturn(listOfOneResult(lat, lng));
        
        Location location = locationFinder.getLocation();
        assertThat(location, CoreMatchers.equalTo(new Location(lat, lng)));
    }

    @Test(expected = LocationException.class)
    public void should_throw_exception_if_no_results_returned() throws Exception {
        when(geocodingConnector.getListOfResults(geoApiContext, ADDRESS))
            .thenReturn(new GeocodingResult[0]);
        
        locationFinder.getLocation();
    }
    
    @Test(expected = LocationException.class)
    public void should_throw_exception_if_more_than_one_result_returned() throws Exception {
        when(geocodingConnector.getListOfResults(geoApiContext, ADDRESS))
            .thenReturn(new GeocodingResult[3]);
        
        locationFinder.getLocation();
    }
    
    private GeocodingResult[] listOfOneResult(double lat, double lng) {
        return new GeocodingResult[] {geocodingResult(lat, lng)};
    }

    protected GeocodingResult geocodingResult(double lat, double lng) {
        GeocodingResult geocodingResult = new GeocodingResult();
        geocodingResult.geometry = new Geometry();
        geocodingResult.geometry.location = new LatLng(lat, lng);
        return geocodingResult;
    }

}
