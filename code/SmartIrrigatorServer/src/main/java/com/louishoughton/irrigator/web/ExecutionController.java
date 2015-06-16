package com.louishoughton.irrigator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("executions")
public class ExecutionController {

    private ListExecutionsService listService;
    private ExecutionDetailService detailService;

    @Autowired
    public ExecutionController(ListExecutionsService listService, ExecutionDetailService detailService) {
        this.listService = listService;
        this.detailService = detailService;
    }

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public List<ExecutionListItem> list(@PathVariable int from) {
        return listService.list(from);
    }

    @RequestMapping(value = "/detail/{ids}", method = RequestMethod.GET)
    public DayDetailItem get(@PathVariable String ids) {
        return detailService.get(ids);
    }

}
