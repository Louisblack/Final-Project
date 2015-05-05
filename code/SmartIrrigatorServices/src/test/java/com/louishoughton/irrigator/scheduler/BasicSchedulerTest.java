package com.louishoughton.irrigator.scheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import com.louishoughton.irrigator.forecast.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.louishoughton.irrigator.job.IrrigationJob;
import com.louishoughton.irrigator.job.JobFactory;

@RunWith(MockitoJUnitRunner.class)
public class BasicSchedulerTest {

    private BasicScheduler scheduler;
    private IrrigationJob job;
    @Mock 
    private TaskScheduler taskScheduler;
    @Mock
    private WeatherService weatherService;
    @Mock
    private JobFactory jobFactory;
    
    @Before
    public void setup() {
        job = new IrrigationJob(weatherService, null, null);
        when(jobFactory.newJob()).thenReturn(job);
        scheduler = new BasicScheduler(taskScheduler, jobFactory);
    }
    
    @Test
    public void test_should_schedule_the_task_twice() {
        scheduler.scheduleExecutions();
        verify(taskScheduler, times(2)).schedule(any(Runnable.class), any(LocalDateTime.class));
    }
    
    @Test
    public void test_should_schedule_an_execution_for_morning() throws Exception {
        scheduler.scheduleExecutions();
        verify(taskScheduler).schedule(job, new BasicMorningExecutionDate().getDate());
    }
    
    @Test
    public void test_should_schedule_an_execution_for_evening() throws Exception {
        scheduler.scheduleExecutions();
        verify(taskScheduler).schedule(job, new BasicEveningExecutionDate().getDate());
    }

}
