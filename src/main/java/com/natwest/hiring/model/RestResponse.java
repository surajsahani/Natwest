package com.natwest.hiring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {

    public int statusCode;
    public  String msgResponse;
}
