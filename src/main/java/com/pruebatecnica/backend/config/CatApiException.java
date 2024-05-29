package com.pruebatecnica.backend.config;

public class CatApiException extends RuntimeException {
    public CatApiException(String message) {
        super(message);
    }

    public CatApiException(String message, Throwable cause) {
        super(message, cause);
    }
}