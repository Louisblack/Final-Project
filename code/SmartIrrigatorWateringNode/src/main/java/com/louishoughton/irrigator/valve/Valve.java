package com.louishoughton.irrigator.valve;

public interface Valve {

    void open() throws IrrigationValveException;
    
    void openFor(int seconds) throws IrrigationValveException;
    
    void close() throws IrrigationValveException;
    
    boolean isOpen();
}
