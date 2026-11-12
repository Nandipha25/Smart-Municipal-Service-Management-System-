package com.bcc.municipalapi.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 6.2 Handles exceptions for all controllers in one place
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // invalid request -> 400 Bad Request with a meaningful message
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequest(InvalidRequestException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // anything else
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {

        // Spring's own errors (page not found, wrong method) keep their real status code
        if (ex instanceof ErrorResponse errorResponse) {
            HttpStatus status = HttpStatus.valueOf(errorResponse.getStatusCode().value());
            return buildResponse(status, ex.getMessage());
        }

        // truly unexpected errors are logged so we can see the cause in the Output window
        logger.error("Unexpected error", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong on the server. Please try again later.");
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}