package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.valve.NodeConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("watering")
public class WateringController {

    IrrigationRequestDispatcher requestDispatcher;

    @Autowired
    public WateringController(IrrigationRequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public IrrigationResponse waterNow(IrrigationRequest request) {
        try {
            return requestDispatcher.dispatch(request);
        } catch (NodeConnectionException e) {
            return new IrrigationResponse(false, asList(new Error("Could not connect to node")));
        }
    }

}
