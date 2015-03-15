package com.louishoughton.irrigator.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.louishoughton.irrigator.error.IrrigationError;
import com.louishoughton.irrigator.valve.IrrigationValveException;
import com.louishoughton.irrigator.valve.Valve;

@Controller
@RequestMapping("/irrigate")
public class IrrigationController {

    private static final Logger LOG = LogManager.getLogger(IrrigationController.class);
    
    private Valve valve;
    
    @Autowired
    public IrrigationController(Valve valve) {
        this.valve = valve;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody
    public  IrrigationResponse irrigate(@RequestBody IrrigationRequest request) {
        try {
            valve.openFor(request.getSeconds());
        } catch (IrrigationValveException e) {
            LOG.error(e);
            return new IrrigationResponse(false, new IrrigationError(e.getMessage()));
        }
        return new IrrigationResponse(true);
    }
}
