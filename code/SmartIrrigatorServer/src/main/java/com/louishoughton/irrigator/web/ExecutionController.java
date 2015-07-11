package com.louishoughton.irrigator.web;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@RequestMapping("executions")
public class ExecutionController {

    private ListExecutionsService listService;
    private ExecutionDetailService detailService;

    private static final Logger LOG = getLogger(ExecutionController.class);

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
        try {
            return detailService.get(ids);
        } catch (ParseException e) {
            LOG.error(e);
        }
        return null;
    }

}
