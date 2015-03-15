package com.louishoughton.irrigator.valve;

public class IrrigationValveException extends Exception {

    private static final long serialVersionUID = 4402160390379276610L;

    public IrrigationValveException(String message, Throwable exception) {
        super(message, exception);
    }

    public IrrigationValveException(String message) {
        super(message);
    }
}
