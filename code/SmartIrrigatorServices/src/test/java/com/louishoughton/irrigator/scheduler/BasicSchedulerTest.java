package com.louishoughton.irrigator.scheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasicSchedulerTest {

    private BasicScheduler scheduler;
    private Runnable task;
    @Mock 
    private TaskScheduler taskScheduler;
    
    @Before
    public void setup() {
        task = () -> { };
        scheduler = new BasicScheduler(task, taskScheduler);
    }
    
    @Test
    public void test_should_schedule_the_task_twice() {
        scheduler.scheduleExecutions();
        verify(taskScheduler, times(2)).schedule(any(Runnable.class), any(LocalDateTime.class));
    }
    
    @Test
    public void test_should_schedule_an_execution_for_morning() throws Exception {
        scheduler.scheduleExecutions();
        verify(taskScheduler).schedule(task, new BasicMorningExecutionDate().getDate());
    }
    
    @Test
    public void test_should_schedule_an_execution_for_evening() throws Exception {
        scheduler.scheduleExecutions();
        verify(taskScheduler).schedule(task, new BasicEveningExecutionDate().getDate());
    }

}
