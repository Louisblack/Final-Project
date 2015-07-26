package com.louishoughton.irrigator.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;

@Component
public class ExecutionDetailServiceImpl implements ExecutionDetailService {

    private ExecutionDao executionDao;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm");

    @Autowired
    public ExecutionDetailServiceImpl(ExecutionDao executionDao) {
        this.executionDao = executionDao;
    }

    public DayDetailItem get(String date) throws ParseException {
        List<Execution> executions = 
                executionDao.getExecutionsBetween(calcFrom(date), calcTo(date));
        return new DayDetailItem(executions);
    }

    private Date calcTo(String date) throws ParseException {
        return formatter.parse(date + ":23:59");
    }

    private Date calcFrom(String date) throws ParseException {
        return formatter.parse(date + ":00:00");
    }


}
