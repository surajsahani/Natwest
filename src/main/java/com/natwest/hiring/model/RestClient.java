package com.natwest.hiring.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

@Builder
@Data
public class RestClient {

    private HttpHeaders headerMap;
    private MultiValueMap<String, String> queryMap;
    private Object requestBody;

}
