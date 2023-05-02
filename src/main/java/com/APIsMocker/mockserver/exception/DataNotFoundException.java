package com.APIsMocker.mockserver.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class DataNotFoundException  extends RuntimeException{

    private String errorCode = "404";
	private String errorMessage =  "No Configured Path";
    
    public DataNotFoundException(String message) {
        super(message);
        this.errorMessage =message;
    }
    
}






