package com.louishoughton.irrigator.valve;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;

public class MockGpioPin extends GpioPinImpl {

    private boolean high = false;
    
    public MockGpioPin(GpioController gpio, GpioProvider provider, Pin pin) {
        super(gpio, provider, pin);
    }
    
    @Override
    public void high() {
        high = true;
    }
    
    @Override
    public void low() {
        high = false;
    }
    
    @Override
    public boolean isHigh() {
        return high;
    }
    

    

}
