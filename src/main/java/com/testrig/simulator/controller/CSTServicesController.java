package com.testrig.simulator.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testrig.simulator.exception.DataNotFoundException;
import com.testrig.simulator.exception.UnAuthorizedException;
import com.testrig.simulator.repo.HttpRoundTripRepo;
import com.testrig.simulator.service.HttpRoundTripService;

@RestController
@CrossOrigin(origins = "*")
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

    // @GetMapping("/CustomerDetails")
    // public ResponseEntity<Object> CustomerDetails() throws IOException {

    //     // System.out.println(obj);
    //     ClassPathResource staticDataResource = new ClassPathResource("/Jsons/CustomerDetails.json");
    //     String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

    //     return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readValue(staticDataString, Object.class));

    // }

    // @PostMapping("/CustomerDetails")
    // public ResponseEntity<Object> CustomerDetailsPost() throws IOException {

    //     ClassPathResource staticDataResource = new ClassPathResource("/Jsons/CustomerDetails.json");
    //     String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

    //     return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readValue(staticDataString, Object.class));

    // }

    // @GetMapping("/ServicePointList")
    // public ResponseEntity<Object> ServicePointList() throws IOException {

    //     ClassPathResource staticDataResource = new ClassPathResource("/Jsons/ServicePointList.json");
    //     String staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

    //     return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readValue(staticDataString, Object.class));
    // }


    @GetMapping("/{category}")
    public ResponseEntity<Object> jsonOnCat(@PathVariable String category) throws IOException {


        String staticDataString = null;

        try {
            ClassPathResource staticDataResource = new ClassPathResource("/Jsons/"+category+".json");
            staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DataNotFoundException("Json file not found with name "+category);
        }
        return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readValue(staticDataString, Object.class));

    }
}
