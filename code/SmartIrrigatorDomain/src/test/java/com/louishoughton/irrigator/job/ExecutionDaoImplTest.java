package com.louishoughton.irrigator.job;


import com.louishoughton.irrigator.context.PersistenceContext;
import com.louishoughton.irrigator.context.TestDataSourceContext;
import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.web.Error;
import com.louishoughton.irrigator.web.IrrigationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

import static com.louishoughton.irrigator.forecast.TodaysWeather.HEAVY_RAIN;
import static com.louishoughton.irrigator.forecast.TodaysWeather.MINIMUM_CHANCE_OF_RAIN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class, TestDataSourceContext.class})
public class ExecutionDaoImplTest {

    @Autowired
    private ExecutionDaoImpl dao;

    @Test
    public void should_save_an_execution() throws Exception {

        IrrigationRequest irrigationRequest = new IrrigationRequest(20);
        Forecast forecast = new Forecast(MINIMUM_CHANCE_OF_RAIN, HEAVY_RAIN, 20);
        Execution execution = new Execution(forecast, irrigationRequest);
        dao.save(execution);

        assertThat(execution.getId(), not(0));
    }

    @Test
    public void should_retrieve_execution() throws Exception {

        IrrigationRequest irrigationRequest = new IrrigationRequest(20);
        Forecast forecast = new Forecast(MINIMUM_CHANCE_OF_RAIN, HEAVY_RAIN, 20);
        Execution execution = new Execution(forecast, irrigationRequest);
        dao.save(execution);

        Execution executionFromDb = dao.get(execution.getId());

        assertThat(execution, not(sameInstance(executionFromDb)));
    }

    @Test
    @Transactional
    public void should_save_errors() throws Exception {
        IrrigationRequest irrigationRequest = new IrrigationRequest(20);
        Forecast forecast = new Forecast(MINIMUM_CHANCE_OF_RAIN, HEAVY_RAIN, 20);
        Execution execution =
                new Execution(forecast,
                              irrigationRequest,
                              Arrays.asList(new Error("Oops")));
        dao.save(execution);

        Execution executionFromDb = dao.get(execution.getId());

        assertThat(executionFromDb.getErrors().size(), equalTo(1));
    }
}
