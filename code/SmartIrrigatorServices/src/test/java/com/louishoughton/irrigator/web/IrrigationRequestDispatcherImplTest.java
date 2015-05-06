package com.louishoughton.irrigator.web;

import com.louishoughton.irrigator.valve.NodeConnectionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import static com.louishoughton.irrigator.web.IrrigationRequestDispatcherImpl.NODE_BASE_URL_PROPERTY;
import static com.louishoughton.irrigator.web.IrrigationRequestDispatcherImpl.NODE_IRRIGATION_END_POINT_PROPERTY;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IrrigationRequestDispatcherImplTest {

    @Mock
    private RestTemplate restTemplate;
    
    @Test
    public void test_should_set_url_and_endpoint_from_properties() 
            throws NodeConnectionException, RestClientException, URISyntaxException {
        final String url = "127.0.0.1";
        final String endPoint = "/api/irrigate";
        final URI uri = new URI(url + endPoint);
        final IrrigationRequest request = new IrrigationRequest();

        IrrigationRequestDispatcherImpl dispatcher = 
                new IrrigationRequestDispatcherImpl(restTemplate, url, endPoint);
        
        dispatcher.dispatch(request);
        
        verify(restTemplate).postForObject(uri, request, IrrigationResponse.class);
    }

}
