package com.louishoughton.irrigator.web;

import java.util.Arrays;
import java.util.List;

public class IrrigationResponse {

    private boolean success;
    private List<IrrigationError> errors;
    
    public IrrigationResponse() {
        super();
    }

    public IrrigationResponse(boolean success) {
        super();
        this.success = success;
    }

    public IrrigationResponse(boolean success, List<IrrigationError> errors) {
        super();
        this.success = success;
        this.errors = errors;
    }

    public IrrigationResponse(boolean success, IrrigationError error) {
        this(success, Arrays.asList(error));
    }

    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<IrrigationError> getErrors() {
        return errors;
    }

    public void setErrors(List<IrrigationError> errors) {
        this.errors = errors;
    }
}
