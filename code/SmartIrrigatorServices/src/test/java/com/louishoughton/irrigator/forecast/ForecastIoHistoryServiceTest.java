package com.louishoughton.irrigator.forecast;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.louishoughton.irrigator.context.ApisContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApisContext.class})
public class ForecastIoHistoryServiceTest {

    @Autowired
    private ForecastIoHistoryService service;

    @Test
    @Ignore
    public void testName() throws Exception {
        History history = service.getHistory();
        System.out.print(history.getHighestInchesPerHour());
    }
}
