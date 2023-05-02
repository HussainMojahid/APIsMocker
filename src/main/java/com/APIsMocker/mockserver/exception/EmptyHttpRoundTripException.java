package com.APIsMocker.mockserver.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class EmptyHttpRoundTripException  extends RuntimeException{

    private String errorCode = "204";
	private String errorMessage =  "No Content";
    
    public EmptyHttpRoundTripException(String message) {
        super(message);
        this.errorMessage =message;
    }
    
}






