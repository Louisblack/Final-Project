package com.louishoughton.irrigator.web;


import com.louishoughton.irrigator.job.Execution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.louishoughton.irrigator.web.ExecutionListItem.RAIN_ICON;
import static com.louishoughton.irrigator.web.ExecutionListItem.SUN_ICON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
        ExecutionListItem expected = new ExecutionListItem(DATE_STRING, false, 0, RAIN_ICON);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void should_create_an_object_from_irrigating_executions() throws Exception {
        List<Execution> executions = Arrays.asList(executionWithDuration(10), executionWithDuration(20));
        ExecutionListItem actual = new ExecutionListItem(date, executions);
        ExecutionListItem expected = new ExecutionListItem(DATE_STRING, true, 30, SUN_ICON);

        assertThat(actual, equalTo(expected));
    }

    private Execution executionWithDuration(int duration) {
        Execution execution = new Execution();
        execution.setIrrigationRequest(new IrrigationRequest(duration));
        return execution;
    }
}
