package com.louishoughton.irrigator.web;



public class IrrigationRequest {

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
