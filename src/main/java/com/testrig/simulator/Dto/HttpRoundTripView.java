package com.testrig.simulator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRoundTripView {

    Object request;
    Object response;
    String path;

}
