package com.louishoughton.irrigator.forecast;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append("latitude").append("longitude").build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Location rhs = (Location) obj;
        return new EqualsBuilder().append(latitude, rhs.latitude)
                                  .append(longitude, rhs.longitude)
                                  .isEquals();
    }

}
