package com.APIsMocker.mockserver.controller;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.APIsMocker.mockserver.exception.DataNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/epdm/config/v1")
public class EpdmReceiverConfigurations {

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/receiver-configuration")
    public ResponseEntity<Object> getReceiverConfiguration(HttpServletRequest servletRequest,
            @RequestParam(required = true, name = "configurationType") String configurationType)

            throws JsonMappingException, JsonProcessingException {

        String staticDataString = null;

        try {
            ClassPathResource staticDataResource = new ClassPathResource("/Jsons/" + configurationType + ".json");
            staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DataNotFoundException("Json file not found with name " + configurationType);
        }
        return ResponseEntity.status(HttpStatus.OK).body(objectMapper.readValue(staticDataString, Object.class));
    }

}
