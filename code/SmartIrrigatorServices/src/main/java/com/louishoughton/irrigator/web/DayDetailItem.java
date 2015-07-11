package com.louishoughton.irrigator.web;


import com.louishoughton.irrigator.job.Execution;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DayDetailItem {

    private String date;
    private List<ExecutionDetailItem> executions;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public DayDetailItem(List<Execution> executions) {
        this.date = formatter.format(executions.get(0).getDateRun());
        this.executions = executions
            .stream().map(e -> {
                return new ExecutionDetailItem(e);
            })
            .collect(Collectors.toList());
    }

    public String getDate() {
        return date;
    }

    public List<ExecutionDetailItem> getExecutions() {
        return executions;
    }

    public int getNumberOfExecutions() {
        return executions.size();
    }
}
