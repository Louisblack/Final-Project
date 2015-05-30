package com.louishoughton.irrigator.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("executions")
public class ExecutionController {

    private ListExecutionsService listService;

    @RequestMapping(value = "/{from}", method = RequestMethod.GET)
    public List<ExecutionListItem> list(@PathVariable int from) {
        return listService.list(from);
    }

}
