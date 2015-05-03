package com.louishoughton.irrigator.scheduler;

import com.louishoughton.irrigator.job.JobFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public abstract class AbstractScheduler implements Scheduler {

    public static final String MIDNIGHT_CRON_STRING = "0 0 * * *";
    
    private final JobFactory jobFactory;
    private final TaskScheduler taskScheduler;
    
    public AbstractScheduler(TaskScheduler taskScheduler, JobFactory jobFactory) {
        this.taskScheduler = taskScheduler;
        this.jobFactory = jobFactory;
    }
    
    @Override
    @Scheduled(cron = MIDNIGHT_CRON_STRING)
    public void scheduleExecutions() {
        taskScheduler.schedule(jobFactory.newJob(), getMorningExecutionDate());
        taskScheduler.schedule(jobFactory.newJob(), getEveningExecutionDate());
    }

    protected abstract LocalDateTime getEveningExecutionDate();

    protected abstract LocalDateTime getMorningExecutionDate();

}
