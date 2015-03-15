package com.louishoughton.irrigator.scheduler;

import java.time.LocalDateTime;

public interface TaskScheduler {

    void schedule(Runnable runnable, LocalDateTime startTime);
    
}
