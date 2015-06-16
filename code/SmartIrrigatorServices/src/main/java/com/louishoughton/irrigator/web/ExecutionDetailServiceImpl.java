package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ExecutionDetailServiceImpl implements ExecutionDetailService {

    private ExecutionDao executionDao;

    public static final String SEPARATOR = "::";

    @Autowired
    public ExecutionDetailServiceImpl(ExecutionDao executionDao) {
        this.executionDao = executionDao;
    }

    public DayDetailItem get(String idsString) {
        List<Integer> collect = extractIds(idsString);
        List<Execution> executions = executionDao.get(collect);
        return new DayDetailItem(executions);
    }

    private List<Integer> extractIds(String idsString) {
        String[] split = idsString.split(SEPARATOR);
        return (List<Integer>) Arrays.asList(split)
                .stream().map(i -> {
                    return Integer.parseInt(i);
                })
                .collect(Collectors.toList());
    }

}
