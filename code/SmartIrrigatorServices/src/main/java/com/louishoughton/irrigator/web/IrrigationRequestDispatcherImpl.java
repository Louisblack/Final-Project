package com.louishoughton.irrigator.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.louishoughton.irrigator.valve.NodeConnectionException;

public class IrrigationRequestDispatcherImpl implements IrrigationRequestDispatcher {

    private RestTemplate restTemplate;
    private String nodeBaseUrl;
    private String irrigationEndPoint;
    
    public static final String NODE_URL_ERROR = "Check IP address of watering node";
    public static final String NODE_CONNECTION_ERROR = "Error connecting to node with URI - ";
    public static final String NODE_BASE_URL_PROPERTY = "node.base.url";
    public static final String NODE_IRRIGATION_END_POINT_PROPERTY = "node.irrigation.end.point";
    
    private static final Logger LOG = LogManager.getLogger(IrrigationRequestDispatcherImpl.class);
    

    public IrrigationRequestDispatcherImpl(String url, String endPoint) {
        this.nodeBaseUrl = url;
        this.irrigationEndPoint = endPoint;
    }

    public IrrigationRequestDispatcherImpl(RestTemplate restTemplate, String url, String endPoint) {
        this(url, endPoint);
        this.restTemplate = restTemplate;
    }

    @Override
    public IrrigationResponse dispatch(IrrigationRequest request) throws NodeConnectionException {
        URI url = buildUrl();
        try {
            LOG.info("Dispatching " + request);
            return restTemplate.postForObject(url, request, IrrigationResponse.class);
        } catch (RestClientException e) {
            throw new NodeConnectionException(NODE_CONNECTION_ERROR + url, e);
        }
    }
    
    private URI buildUrl() throws NodeConnectionException {
        try {
            return new URI(nodeBaseUrl + irrigationEndPoint);
        } catch (URISyntaxException e) {
            LOG.error(NODE_URL_ERROR, e);
            throw new NodeConnectionException(NODE_URL_ERROR, e);
        } 
    }

}
