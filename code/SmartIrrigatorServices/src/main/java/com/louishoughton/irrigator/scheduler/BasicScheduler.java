package com.louishoughton.irrigator.scheduler;

import com.louishoughton.irrigator.job.JobFactory;

public class BasicScheduler implements Scheduler {

    private JobFactory jobFactory;
    private TaskScheduler taskScheduler;
    
    /** 
     * Executes the provided Runnable with a basic schedule using
     * the provided TaskScheduler.
     * @param execution The Runnable to be executed
     * @param taskScheduler The TaskScheduler with which to execute the Runnable
     */
    public BasicScheduler(TaskScheduler taskScheduler, JobFactory jobFactory) {
        this.taskScheduler = taskScheduler;
        this.jobFactory = jobFactory;
    }
    
    @Override
    public void scheduleExecutions() {
        taskScheduler.schedule(jobFactory.newJob(), new BasicMorningExecutionDate().getDate());
        taskScheduler.schedule(jobFactory.newJob(), new BasicEveningExecutionDate().getDate());
    }

}
