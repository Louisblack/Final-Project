package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionDetailServiceImplTest {

    @Mock
    private ExecutionDao dao;
    private ExecutionDetailServiceImpl service;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy:hh:mm");

    @Before
    public void setUp() throws Exception {
        service = new ExecutionDetailServiceImpl(dao);
    }

    @Test
    public void should_return_executions_for_the_day() throws Exception {
        Date from = formatter.parse("02/10/2015:00:00");
        Date to = formatter.parse("02/10/2015:23:59");
        when(dao.getExecutionsBetween(from, to)).thenReturn(Arrays.asList(new Execution(), new Execution()));

        DayDetailItem dayDetailItem = service.get("02/10/2015");

        assertThat(dayDetailItem.getNumberOfExecutions(), CoreMatchers.equalTo(2));
    }
}