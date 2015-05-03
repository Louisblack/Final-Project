package com.louishoughton.irrigator.scheduler;

import com.louishoughton.irrigator.job.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BasicScheduler extends AbstractScheduler {

    @Autowired
    public BasicScheduler(TaskScheduler taskScheduler, JobFactory jobFactory) {
        super(taskScheduler, jobFactory);
    }

    @Override
    protected LocalDateTime getEveningExecutionDate() {
        return new BasicEveningExecutionDate().getDate();
    }

    @Override
    protected LocalDateTime getMorningExecutionDate() {
        return new BasicMorningExecutionDate().getDate();
    }

}
