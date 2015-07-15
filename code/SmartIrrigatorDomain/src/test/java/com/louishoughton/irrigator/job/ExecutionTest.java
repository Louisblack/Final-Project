package com.louishoughton.irrigator.job;

import com.louishoughton.irrigator.web.Error;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class ExecutionTest {

    @Test
    public void should_return_just_the_error_messages() throws Exception {
        String message = "Oops!";
        Execution execution = new Execution();
        execution.setErrors(asList(new Error(message)));

        assertThat(execution.getErrorMessages(), equalTo(asList(message)));
    }
}
