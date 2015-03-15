package com.louishoughton.irrigator.scheduler;

public class BasicEveningExecutionDate extends BasicExecutionDate {

    private static final int HOUR = 20;
    private static final int MINUTE = 0;
    
    public BasicEveningExecutionDate() {
        super(HOUR, MINUTE);
    }
    
}
