package com.louishoughton.irrigator.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.louishoughton.irrigator.valve.IrrigationValveException;
import com.louishoughton.irrigator.valve.Valve;

@RunWith(MockitoJUnitRunner.class)
public class IrrigationControllerTest {

    @Mock
    private Valve valve;
    
    private IrrigationController controller;
    
    @Before
    public void setup() {
        controller = new IrrigationController(valve);
    }
    
    @Test
    public void test_should_pass_the_amount_of_seconds_to_valve() throws IrrigationValveException {
        int seconds = 2;
        controller.irrigate(new IrrigationRequest(seconds));
        verify(valve).openFor(seconds);
    }
    
    @Test
    public void test_should_pass_back_success_response() throws Exception {
        IrrigationResponse response = controller.irrigate(new IrrigationRequest(1));
        assertTrue(response.isSuccess());
    }
    
    @Test
    public void test_should_pass_back_failure_response() throws Exception {
        int seconds = 1;
        String message = "it went wrong";
        doThrow(new IrrigationValveException(message)).when(valve).openFor(seconds);
        IrrigationResponse response = controller.irrigate(new IrrigationRequest(seconds));
        assertFalse(response.isSuccess());
    }
    
    @Test
    public void test_should_pass_back_exception_as_error() throws Exception {
        int seconds = 1;
        String message = "it went wrong";
        doThrow(new IrrigationValveException(message)).when(valve).openFor(seconds);
        IrrigationResponse response = controller.irrigate(new IrrigationRequest(seconds));
        assertFalse(response.getErrors().isEmpty());
    }
    
    @Test
    public void test_should_pass_back_exception_message_as_error() throws Exception {
        int seconds = 1;
        String message = "it went wrong";
        doThrow(new IrrigationValveException(message)).when(valve).openFor(seconds);
        IrrigationResponse response = controller.irrigate(new IrrigationRequest(seconds));
        Error error = response.getErrors().get(0);
        assertThat(error.getMessage(), equalTo(message));
    }

}
