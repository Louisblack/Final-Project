package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;

public class JobFactoryImpl implements JobFactory {

    private IrrigationRequestDispatcher irrigationRequestDispatcher;
    
    @Override
    public IrrigationJob newJob() {
        return new IrrigationJob(irrigationRequestDispatcher);
    }
}
