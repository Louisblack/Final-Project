package com.louishoughton.irrigator.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.louishoughton.irrigator.valve.NodeConnectionException;
import com.louishoughton.irrigator.web.IrrigationRequest;
import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;

public class IrrigationJob implements Runnable {

    private IrrigationRequestDispatcher requestDispatcher;
    private static final Logger LOG = LogManager.getLogger(IrrigationJob.class);
    
    public static final String REQUEST_DISPATCH_ERROR = "Cannot dispatch request to watering node"; 
    
    public IrrigationJob(IrrigationRequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    @Override
    public void run() {
        int timeToWater = 5;
        try {
            requestDispatcher.dispatch(new IrrigationRequest(timeToWater));
        } catch (NodeConnectionException e) {
            LOG.error(REQUEST_DISPATCH_ERROR, e);
        }
    }
}
