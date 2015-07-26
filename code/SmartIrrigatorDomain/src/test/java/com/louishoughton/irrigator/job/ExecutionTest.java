package com.louishoughton.irrigator.job;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.louishoughton.irrigator.web.Error;


public class ExecutionTest {

    @Test
    public void should_return_just_the_error_messages() throws Exception {
        String message = "Oops!";
        Execution execution = new Execution();
        execution.setErrors(asList(new Error(message)));

        assertThat(execution.getErrorMessages(), equalTo(asList(message)));
    }
}
