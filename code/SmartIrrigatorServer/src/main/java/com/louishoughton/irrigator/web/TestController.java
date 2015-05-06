package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.job.Execution;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Execution execution() {
        return new Execution(new Forecast(60, 0.3, 20));
    }
}
