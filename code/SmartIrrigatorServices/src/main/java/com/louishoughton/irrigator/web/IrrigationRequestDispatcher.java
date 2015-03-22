package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.valve.NodeConnectionException;

public interface IrrigationRequestDispatcher {

    IrrigationResponse dispatch(IrrigationRequest request) throws NodeConnectionException;
    
}
