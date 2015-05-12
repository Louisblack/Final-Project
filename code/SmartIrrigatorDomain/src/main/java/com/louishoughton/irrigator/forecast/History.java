package com.louishoughton.irrigator.forecast;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Immutable
public class History {

    @Id
    @GeneratedValue
    private int id;
    private double highestInchesPerHour;

    public History() {
    }

    public History(double highestInchesPerHour) {
        this.highestInchesPerHour = highestInchesPerHour;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHighestInchesPerHour() {
        return highestInchesPerHour;
    }

    public void setHighestInchesPerHour(double highestInchesPerHour) {
        this.highestInchesPerHour = highestInchesPerHour;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
