package com.APIsMocker.mockserver.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRoundTripDelete {

    Object request;
    String path;

}
