package com.testrig.simulator.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.testrig.simulator.Dto.errorResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AdviceException {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> EmptyPasswordException(HttpRequestMethodNotSupportedException exception) {

        return new ResponseEntity<>(new errorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not allowed",
                false, HttpStatus.METHOD_NOT_ALLOWED.name()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EmptyHttpRoundTripException.class)
    public ResponseEntity<Object> EmptyHttpRoundTripException(EmptyHttpRoundTripException exception) {

        return new ResponseEntity<>(new errorResponse(HttpStatus.NOT_FOUND.value(), "There is No Data Available", false,
                HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InexactRequestBodyException.class)
    public ResponseEntity<Object> InexactRequestBodyException(InexactRequestBodyException exception) {

        return new ResponseEntity<>(new errorResponse(HttpStatus.BAD_REQUEST.value(), "Inexact Request Body", false,
                HttpStatus.BAD_REQUEST.name()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoConfiguredPathException.class)
    public ResponseEntity<Object> NoConfiguredPathException(NoConfiguredPathException exception) {

        return new ResponseEntity<>(new errorResponse(HttpStatus.NOT_FOUND.value(), "Path is Not Configured", false,
                HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> NotFoundException(DataNotFoundException exception) {

        return new ResponseEntity<>(
                new errorResponse(HttpStatus.NOT_FOUND.value(), "No Such Data", false, HttpStatus.NOT_FOUND.name()),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> NotFoundException(UnAuthorizedException exception) {

        return new ResponseEntity<>(
                new errorResponse(HttpStatus.FORBIDDEN.value(), "UNAUTHORIZED", false, HttpStatus.FORBIDDEN.name()),
                HttpStatus.FORBIDDEN);

    }

}
