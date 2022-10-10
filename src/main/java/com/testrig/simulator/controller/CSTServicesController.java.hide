package com.testrig.simulator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testrig.simulator.exception.UnAuthorizedException;
import com.testrig.simulator.repo.HttpRoundTripRepo;
import com.testrig.simulator.service.HttpRoundTripService;

@RestController
@RequestMapping("/CSTServices")
public class CSTServicesController {

    @Autowired
    HttpRoundTripRepo reqRepo;

    @Autowired
    HttpRoundTripService service;

    @Autowired
    ObjectMapper objectMapper;

    final String Header = "Basic S2FocmFtYWE6UEAkJHcwcmRfMDUyMDIw";

    // /CSTServices/NearestBrancheLists

    @PostMapping("/NearestBrancheLists")
    public ResponseEntity<Object> NearestBrancheLists(@RequestBody Object obj, HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws JsonProcessingException {

        String AuthHeader = servletRequest.getHeader("Authorization");

        if (AuthHeader != null && AuthHeader.equals(Header)) {

            System.out.println(AuthHeader);

            String a = service.resFinder(obj);

            Object o = objectMapper.readValue(a, Object.class);
            return ResponseEntity.ok().body(o);

        } else {

            throw new UnAuthorizedException();

        }

    }

}
