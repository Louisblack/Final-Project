package com.louishoughton.irrigator.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BasicExecutionDate implements ExecutionDate {

    private LocalDateTime executionDate;
    
    public BasicExecutionDate(int hour, int minute) {
        executionDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, minute));
    }
    
    @Override
    public LocalDateTime getDate() {
        return executionDate;
    }

}
