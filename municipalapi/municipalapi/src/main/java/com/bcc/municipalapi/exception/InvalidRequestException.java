package com.bcc.municipalapi.exception;

// 6.2 Custom exception thrown when a service request is invalid
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}