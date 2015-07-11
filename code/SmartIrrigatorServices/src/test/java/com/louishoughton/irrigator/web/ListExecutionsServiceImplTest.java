package com.louishoughton.irrigator.web;


import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListExecutionsServiceImplTest {

    private static final String DATE_STRING = "01/01/2015:17:30";
    private Date date;

    @Mock
    private ExecutionDao executionDao;

    private ListExecutionsServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new ListExecutionsServiceImpl(executionDao);
        date = new SimpleDateFormat("dd/MM/yyyy:hh:mm").parse(DATE_STRING);
    }

    @Test
    public void should_create_a_list_with_one_item() throws Exception {

        when(executionDao.list(0, 14)).thenReturn(Arrays.asList(getExecution(), getExecution()));

        List<ExecutionListItem> list = service.list(1);

        assertThat(list.size(), equalTo(1));
    }

    private Execution getExecution() {
        Execution execution = new Execution();
        execution.setDateRun(date);
        return execution;
    }
}
