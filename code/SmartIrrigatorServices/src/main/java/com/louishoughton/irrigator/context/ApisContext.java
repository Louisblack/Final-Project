package com.louishoughton.irrigator.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.google.maps.GeoApiContext;
import com.louishoughton.irrigator.forecast.GoogleMapsLocationFinder;
import com.louishoughton.irrigator.forecast.LocationFinder;



@Configuration
@ComponentScan("com.louishoughton.irrigator.forecast")
@PropertySource({"classpath:/api-keys.properties", "classpath:/options.properties" })
public class ApisContext {

    @Autowired 
    Environment env;
    
    @Bean
    public GeoApiContext geoApiContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey(env.getProperty("google"));
        return geoApiContext;
    }
    
    @Bean 
    public ForecastIO forecastIo() {
        return new ForecastIO(env.getProperty("forecastio"));
    }
    
    @Bean
    public LocationFinder locationFinder() {
        return new GoogleMapsLocationFinder(geoApiContext(), env.getProperty("postcode"));
    }
}
