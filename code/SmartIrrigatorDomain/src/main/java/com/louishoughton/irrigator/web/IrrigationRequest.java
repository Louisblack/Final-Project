package com.louishoughton.irrigator.web;

import java.io.Serializable;


public class IrrigationRequest implements Serializable {

    private static final long serialVersionUID = 7037513462588355768L;
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

    public int getSeconds() {
        return seconds;
    }
}
