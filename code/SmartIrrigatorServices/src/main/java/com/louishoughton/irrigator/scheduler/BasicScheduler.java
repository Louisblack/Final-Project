package com.louishoughton.irrigator.scheduler;

public class BasicScheduler implements Scheduler {

    private TaskScheduler taskScheduler;
    private Runnable execution;
    private ExecutionDate morningExecutionDate;
    private ExecutionDate eveningExecutionDate;
    
    /** 
     * Executes the provided Runnable with a basic schedule using
     * the provided TaskScheduler.
     * @param execution The Runnable to be executed
     * @param taskScheduler The TaskScheduler with which to execute the Runnable
     */
    public BasicScheduler(Runnable execution, TaskScheduler taskScheduler) {
        this.execution = execution;
        this.taskScheduler = taskScheduler;
        this.morningExecutionDate = new BasicMorningExecutionDate();
        this.eveningExecutionDate = new BasicEveningExecutionDate();
    }
    
    @Override
    public void scheduleExecutions() {
        taskScheduler.schedule(execution, morningExecutionDate.getDate());
        taskScheduler.schedule(execution, eveningExecutionDate.getDate());
    }

}
