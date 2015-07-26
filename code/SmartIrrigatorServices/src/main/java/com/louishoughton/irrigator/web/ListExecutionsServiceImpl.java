package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.job.Execution;
import com.louishoughton.irrigator.job.ExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ListExecutionsServiceImpl implements ListExecutionsService {

    public static final int RECORDS_TO_RETRIEVE = 14;
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
        List<Execution> executions = executionDao.list(from - 1, from + RECORDS_TO_RETRIEVE - 1);
        return executions
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
