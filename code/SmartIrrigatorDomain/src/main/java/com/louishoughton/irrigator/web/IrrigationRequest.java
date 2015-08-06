package com.louishoughton.irrigator.web;


import com.louishoughton.irrigator.valve.IrrigationValveException;
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
public class IrrigationRequest {

    public static final int MAXIMUM_DURATION = 240;
    @Id
    @GeneratedValue()
    private int id;

    private int seconds;

    public IrrigationRequest(int seconds) {
        this.seconds = seconds;
    }
    
    public IrrigationRequest() {
        super();
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() throws IrrigationValveException {
        if (!isValid()) {
            throw new IrrigationValveException("Number of seconds above maximum");
        }
        return seconds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean isValid() {
        return seconds <= MAXIMUM_DURATION;
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
