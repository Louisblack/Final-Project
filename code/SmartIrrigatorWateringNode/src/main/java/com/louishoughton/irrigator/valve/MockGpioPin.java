package com.louishoughton.irrigator.valve;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import org.springframework.stereotype.Component;

@Component
public class MockGpioPin extends GpioPinImpl {

    private boolean high = false;
    
    public MockGpioPin() {
        super(null, null, null);
    }
    
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
    
    @Override
    public PinState getState() {
        if (high) {
            return PinState.HIGH;
        } else {
            return PinState.LOW;
        }
    }

}
