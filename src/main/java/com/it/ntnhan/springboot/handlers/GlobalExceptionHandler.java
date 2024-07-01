package com.it.ntnhan.springboot.handlers;

import com.it.ntnhan.springboot.exceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception,
                                                                    HttpServletRequest request) {
        Map<String, String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(err -> err.getDefaultMessage() != null)
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiError.builder()
                                .path(request.getServletPath())
                                .validationErrors(validationErrors)
                                .build()
                );
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<?> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception,
                                                               HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiError.builder()
                                .path(request.getServletPath())
                                .message(exception.getMessage())
                                .build()
                );
    }
}
