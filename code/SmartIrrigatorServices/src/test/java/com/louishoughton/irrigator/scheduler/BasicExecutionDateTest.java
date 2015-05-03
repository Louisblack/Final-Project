package com.louishoughton.irrigator.scheduler;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasicExecutionDateTest {

    @Test
    public void test_should_create_a_date_for_8_30() throws Exception {
        int hour = 8;
        int minute = 30;
        BasicExecutionDate executionDate = new BasicExecutionDate(hour, minute);
        LocalDateTime expected = LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, minute));
        assertThat(executionDate.getDate(), equalTo(expected));
    }

}
