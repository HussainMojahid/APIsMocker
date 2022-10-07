package com.testrig.simulator.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class NoConfiguredPathException  extends RuntimeException{

    private String errorCode = "404";
	private String errorMessage =  "No Configured Path";
    
    public NoConfiguredPathException(String message) {
        super(message);
        this.errorMessage =message;
    }
    
}






