package com.louishoughton.irrigator.scheduler;

public class BasicMorningExecutionDate extends BasicExecutionDate {

    private static final int HOUR = 6;
    private static final int MINUTE = 0;
    
    public BasicMorningExecutionDate() {
        super(HOUR, MINUTE);
    }
}
