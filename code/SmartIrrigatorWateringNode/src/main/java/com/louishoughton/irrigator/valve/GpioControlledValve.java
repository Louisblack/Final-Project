package com.louishoughton.irrigator.valve;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class GpioControlledValve implements Valve {

    public static final Pin SOLENOID_PIN = RaspiPin.GPIO_00;
    public static final String PIN_NAME = "solenoidPin";
    public static final String VALVE_ALREADY_OPEN_MESSAGE = "The valve is already open";
    public static final String VALVE_ALREADY_CLOSED_MESSAGE = "The valve is already closed";
    private static final Logger LOG = LogManager.getLogger(GpioControlledValve.class);
    
    private GpioPinDigitalOutput gpioPin;
    
    public GpioControlledValve() {
        GpioController gpioController = GpioFactory.getInstance();
        gpioPin = gpioController.provisionDigitalOutputPin(SOLENOID_PIN, PIN_NAME, PinState.LOW);
    }
    
    GpioControlledValve(GpioPinDigitalOutput gpioPin) {
        this.gpioPin = gpioPin;
    }
    
    @Override
    public void openFor(int seconds) throws IrrigationValveException {
        open();
        sleep(seconds);
        close();
    }

    @Override
    public void open() throws IrrigationValveException {
        if (!isOpen()) {
            gpioPin.high();
            LOG.info("Pin state is now " + gpioPin.getState());
        } else {
            throw new IrrigationValveException(VALVE_ALREADY_OPEN_MESSAGE);
        }
    }

    @Override
    public void close() throws IrrigationValveException {
        if (isOpen()) {
            gpioPin.low();
            LOG.info("Pin state is now " + gpioPin.getState());
        } else {
            throw new IrrigationValveException(VALVE_ALREADY_CLOSED_MESSAGE);
        }
        
    }

    @Override
    public boolean isOpen() {
        return gpioPin.isHigh();
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
    }
}
