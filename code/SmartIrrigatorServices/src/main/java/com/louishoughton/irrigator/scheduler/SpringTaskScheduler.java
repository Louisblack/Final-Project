package com.louishoughton.irrigator.scheduler;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class SpringTaskScheduler implements TaskScheduler {

    private org.springframework.scheduling.TaskScheduler taskScheduler;
    
    public SpringTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(3);
        threadPoolTaskScheduler.initialize();
        taskScheduler = threadPoolTaskScheduler;
    }
    
    @Override
    public void schedule(Runnable runnable, LocalDateTime startTime) {
        taskScheduler.schedule(runnable, getDate(startTime));
    }
    
    public Date getDate(LocalDateTime startTime) {
        Instant asInstant = startTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(asInstant);
    }

}
