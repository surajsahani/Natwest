package com.natwest.hiring.service;

import com.natwest.hiring.model.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    public <T> ResponseEntity<T> sendRequest(RestClient restClient, HttpMethod httpMethod, String url, Class<T> returnType) {
        if (restClient.getHeaderMap() == null)
            restClient.setHeaderMap(new HttpHeaders());

        restClient.getHeaderMap().addIfAbsent(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);

        if (restClient.getQueryMap() != null)
            url = UriComponentsBuilder.fromHttpUrl(url).queryParams(restClient.getQueryMap()).build().toUriString();

        HttpEntity<Object> httpEntity = new HttpEntity<>(restClient.getRequestBody(), restClient.getHeaderMap());
        ResponseEntity<T> response = restTemplate.exchange(url, httpMethod, httpEntity, returnType);
        return response;
    }
}
