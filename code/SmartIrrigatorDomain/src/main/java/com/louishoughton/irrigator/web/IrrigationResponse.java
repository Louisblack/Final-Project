package com.louishoughton.irrigator.web;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IrrigationResponse {

    private boolean success;
    private List<Error> errors;
    
    public IrrigationResponse() {
        errors = new ArrayList<>();
    }

    public IrrigationResponse(boolean success) {
        this();
        this.success = success;
    }

    public IrrigationResponse(boolean success, List<Error> errors) {
        super();
        this.success = success;
        this.errors = errors;
    }

    public IrrigationResponse(boolean success, Error error) {
        this(success, Arrays.asList(error));
    }

    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
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
