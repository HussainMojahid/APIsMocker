package com.testrig.simulator.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class InexactRequestBodyException  extends RuntimeException{

    private String errorCode = "400";
	private String errorMessage =  "Inexact Request Body";
    
    public InexactRequestBodyException(String message) {
        super(message);
        this.errorMessage =message;
    }
    
}






