package com.group.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
        MethodArgumentNotValidException ex,
        HttpServletRequest req) {
            String message = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .findFirst()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .orElse("Invalid Request");

            return ResponseEntity.badRequest().body(
                ApiError.of(400,
                            "Bad Request",
                            message,
                            req.getRequestURI())
            );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintValidation(
        ConstraintViolationException ex,
        HttpServletRequest req) {
            return ResponseEntity.badRequest().body(
                ApiError.of(400,
                            "Bad Request",
                            ex.getMessage(),
                            req.getRequestURI())
            );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(
        Exception ex,
        HttpServletRequest req) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiError.of(500,
                            "Internal Server Error",
                            "An unexpected error occur.",
                            req.getRequestURI())
            );
    }
}
