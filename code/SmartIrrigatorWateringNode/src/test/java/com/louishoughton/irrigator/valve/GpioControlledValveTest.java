package com.louishoughton.irrigator.valve;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GpioControlledValveTest {

    private GpioControlledValve valve;
    
    
    @Before
    public void setup() {
        valve = new GpioControlledValve(new MockGpioPin(null, null, null));
    }
    
    @Test
    public void test_should_start_closed() throws IrrigationValveException {
        assertFalse(valve.isOpen());
    }

    @Test
    public void test_should_open_valve() throws IrrigationValveException {
        valve.open();
        assertTrue(valve.isOpen());
    }
    
    @Test
    public void test_should_close_valve() throws IrrigationValveException {
        valve.open();
        valve.close();
        assertFalse(valve.isOpen());
    }
    
    @Test(expected = IrrigationValveException.class)
    public void test_should_throw_exception_when_valve_already_open() throws Exception {
        valve.open();
        valve.open();
    }
    
    @Test(expected = IrrigationValveException.class)
    public void test_should_throw_exception_when_valve_already_closed() throws Exception {
        valve.close();
    }

}
