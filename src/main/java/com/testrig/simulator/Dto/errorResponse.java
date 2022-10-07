package com.testrig.simulator.Dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class errorResponse {

    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS"));
    private int status;
    private String message;
    private boolean responseStatus;
    private String statusCode;

    public errorResponse(int status, String message, boolean responseStatus, String statusCode) {
        this.status = status;
        this.message = message;
        this.responseStatus = responseStatus;
        this.statusCode = statusCode;
    }

}
