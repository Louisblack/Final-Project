package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ListExecutionsServiceImpl {

    public static final int RECORDS_TO_RETRIEVE = 7;
    private ExecutionDao executionDao;

    @Autowired
    public ListExecutionsServiceImpl(ExecutionDao executionDao) {
        this.executionDao = executionDao;
    }

    public List<ExecutionListItem> list(int from) {

        Map<Date, List<Execution>> groupedByDate = findExecutionsGroupedByDate(from);
        return formatForFrontEnd(groupedByDate);
    }

    private Map<Date, List<Execution>> findExecutionsGroupedByDate(int from) {
        return executionDao.list(from, from + RECORDS_TO_RETRIEVE)
                .stream()
                .collect(Collectors.groupingBy(Execution::getDateRunWithNoTime));
    }

    private List<ExecutionListItem> formatForFrontEnd(Map<Date, List<Execution>> groupedByDate) {
        return groupedByDate.keySet()
                .stream()
                .sorted(Comparator.<Date>reverseOrder())
                .map(date -> {
                    return new ExecutionListItem(date, groupedByDate.get(date));
                })
                .collect(Collectors.toList());
    }

}
