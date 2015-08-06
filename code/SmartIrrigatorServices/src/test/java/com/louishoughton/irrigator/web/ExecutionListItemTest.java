package com.louishoughton.irrigator.web;


import static com.louishoughton.irrigator.web.ExecutionListItem.ERROR_ICON;
import static com.louishoughton.irrigator.web.ExecutionListItem.RAIN_ICON;
import static com.louishoughton.irrigator.web.ExecutionListItem.SUN_ICON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.louishoughton.irrigator.job.Execution;

public class ExecutionListItemTest {

    private static final String DATE_STRING = "01/01/2015";
    private Date date;

    @Before
    public void setUp() throws Exception {
        date = new SimpleDateFormat("dd/MM/yyyy").parse(DATE_STRING);
    }

    @Test
    public void should_create_an_object_from_rainy_executions() throws Exception {
        List<Execution> executions = Arrays.asList(new Execution(), new Execution());
        ExecutionListItem actual = new ExecutionListItem(date, executions);
        ExecutionListItem expected = new ExecutionListItem(DATE_STRING, 
                                                           false, 
                                                           0, 
                                                           RAIN_ICON);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void should_create_an_object_from_irrigating_executions() throws Exception {
        List<Execution> executions = Arrays.asList(executionWithDuration(10), 
                                                   executionWithDuration(20));
        ExecutionListItem actual = new ExecutionListItem(date, executions);
        ExecutionListItem expected = new ExecutionListItem(DATE_STRING, 
                                                           true, 
                                                           30, 
                                                           SUN_ICON);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void should_have_an_error_icon_if_execution_has_errors() throws Exception {
        List<Execution> executions = Arrays.asList(executionWithDuration(10), executionWithError());
        ExecutionListItem executionListItem = new ExecutionListItem(date, executions);
        assertThat(executionListItem.getIconClass(), equalTo(ERROR_ICON));
    }

    private Execution executionWithDuration(int duration) {
        Execution execution = new Execution();
        execution.setIrrigationRequest(new IrrigationRequest(duration));
        return execution;
    }

    private Execution executionWithError() {
        Execution execution = new Execution();
        execution.setErrors(Arrays.asList(new Error("Oops")));
        return execution;
    }
}
