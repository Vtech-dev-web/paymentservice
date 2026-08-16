package com.ecart.paymentservice.config;

import com.ecart.paymentservice.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        
		ExceptionResponse errorObj = new ExceptionResponse(
				String.valueOf(HttpStatus.NOT_FOUND.value()),
                "Not Found",
                ex.getMessage()
        );
        
        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }

    // Generic fallback handler for any unhandled application errors (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(
            Exception ex, HttpServletRequest request) {
        
    	ExceptionResponse errorObj = new ExceptionResponse(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "Internal Server Error",
                "An unexpected database or system error occurred."
        );
        
        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
