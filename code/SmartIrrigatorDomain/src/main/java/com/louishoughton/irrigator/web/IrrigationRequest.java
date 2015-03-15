package com.louishoughton.irrigator.web;

public class IrrigationRequest {

    private int seconds;

    public IrrigationRequest(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
