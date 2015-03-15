package com.louishoughton.irrigator.valve;

import com.louishoughton.irrigator.valve.IrrigationValveException;

public interface Valve {

    void open() throws IrrigationValveException;
    
    void openFor(int seconds) throws IrrigationValveException;
    
    void close() throws IrrigationValveException;
    
    boolean isOpen();
}
