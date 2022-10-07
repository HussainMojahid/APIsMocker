package com.testrig.simulator.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UnAuthorizedException  extends RuntimeException{

    private String errorCode = "401";
	private String errorMessage =  "unAuthorized";
    
    public UnAuthorizedException(String message) {
        super(message);
        this.errorMessage =message;
    }
    
}






