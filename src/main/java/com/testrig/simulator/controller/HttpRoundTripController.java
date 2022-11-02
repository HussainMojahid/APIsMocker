package com.testrig.simulator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testrig.simulator.Dto.HttpRoundTripDelete;
import com.testrig.simulator.Dto.HttpRoundTripView;
import com.testrig.simulator.Dto.HttpRoundTripViewId;
import com.testrig.simulator.repo.HttpRoundTripRepo;
import com.testrig.simulator.service.HttpRoundTripService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class HttpRoundTripController {

    @Autowired
    HttpRoundTripRepo reqRepo;

    @Autowired
    HttpRoundTripService service;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/add")
    public ResponseEntity<String> addRequestResponse(@RequestBody HttpRoundTripView requestView)
            throws JsonProcessingException {

        service.addRequestResponse(requestView);

        return ResponseEntity.status(HttpStatus.CREATED).body("Request Response Saved");

    }

    @GetMapping("/getall")
    public ResponseEntity<List<HttpRoundTripViewId>> addRequestResponse()
            throws JsonProcessingException {

        List<HttpRoundTripViewId> list = service.allRequestResponse();

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRequestResponse(@RequestBody HttpRoundTripDelete httpRoundTripDelete)
            throws JsonProcessingException {

        service.deleteHttpRoundTrip(httpRoundTripDelete);
        return ResponseEntity.ok().body("Data Deleted");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHttpRoundTripById(@PathVariable("id") Integer id)
            throws JsonProcessingException {

        try {

            service.deleteHttpRoundTripById(id);

            return ResponseEntity.ok().body("Data Deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data with this Id " + id + " Not Present");
        }

    }

    @PostMapping("**")
    public ResponseEntity<Object> genricController(HttpServletRequest servletRequest, @RequestBody Object obj)

            throws JsonMappingException, JsonProcessingException {

        System.out.println(servletRequest.getRequestURI());

        HttpRoundTripView req = service.findReqResByPath(servletRequest.getRequestURI(), obj);

        return ResponseEntity.status(HttpStatus.OK).body(req.getResponse());
    }

}
