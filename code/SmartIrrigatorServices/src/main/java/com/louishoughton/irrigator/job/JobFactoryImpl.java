package com.louishoughton.irrigator.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.louishoughton.irrigator.web.IrrigationRequestDispatcher;

@Component
public class JobFactoryImpl implements JobFactory {

    private IrrigationRequestDispatcher irrigationRequestDispatcher;

    @Autowired
    public JobFactoryImpl(
            IrrigationRequestDispatcher irrigationRequestDispatcher) {
        super();
        this.irrigationRequestDispatcher = irrigationRequestDispatcher;
    }

    @Override
    public IrrigationJob newJob() {
        return new IrrigationJob(irrigationRequestDispatcher);
    }
}
