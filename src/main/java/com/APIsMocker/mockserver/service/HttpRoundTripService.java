package com.APIsMocker.mockserver.service;


import java.util.List;

import com.APIsMocker.mockserver.Dto.HttpRoundTripDelete;
import com.APIsMocker.mockserver.Dto.HttpRoundTripView;
import com.APIsMocker.mockserver.Dto.HttpRoundTripViewId;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface HttpRoundTripService {

    public String resFinder(Object obj) throws JsonProcessingException;

    public void addRequestResponse(HttpRoundTripView requestView) throws JsonProcessingException;

    public void deleteHttpRoundTrip(HttpRoundTripDelete object) throws JsonProcessingException;

    public List<HttpRoundTripViewId> allRequestResponse();


    public HttpRoundTripView findReqResByPath(String requestURI, Object obj) throws JsonProcessingException;

    public void deleteHttpRoundTripById(Integer id);

    
}
