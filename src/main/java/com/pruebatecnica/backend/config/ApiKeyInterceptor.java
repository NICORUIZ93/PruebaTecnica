package com.pruebatecnica.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyInterceptor implements ClientHttpRequestInterceptor {

    private final String apiKey;

    public ApiKeyInterceptor(@Value("${thecatapi.apikey}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("x-api-key", apiKey);
        return execution.execute(request, body);
    }
}