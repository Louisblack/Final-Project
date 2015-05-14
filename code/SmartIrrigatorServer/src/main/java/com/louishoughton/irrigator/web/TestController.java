package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.forecast.Forecast;
import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ExecutionDao executionDao;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Execution> execution() {
        return executionDao.list();
    }
}
