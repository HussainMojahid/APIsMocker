package com.testrig.simulator.service;


import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testrig.simulator.Dto.HttpRoundTripDelete;
import com.testrig.simulator.Dto.HttpRoundTripView;
import com.testrig.simulator.Dto.HttpRoundTripViewId;

public interface HttpRoundTripService {

    public String resFinder(Object obj) throws JsonProcessingException;

    public void addRequestResponse(HttpRoundTripView requestView) throws JsonProcessingException;

    public void deleteHttpRoundTrip(HttpRoundTripDelete object) throws JsonProcessingException;

    public List<HttpRoundTripViewId> allRequestResponse();


    public HttpRoundTripView findReqResByPath(String requestURI, Object obj) throws JsonProcessingException;

    public void deleteHttpRoundTripById(Integer id);

    
}
