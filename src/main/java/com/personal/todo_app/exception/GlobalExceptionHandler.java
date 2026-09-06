package com.personal.todo_app.exception;

import com.personal.todo_app.exception.auth.InvalidCredentialsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception exception, HttpServletRequest httpServletRequest) {

        log.error("Unexpected internal server error", exception);

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(
                        ErrorResponse.builder()
                                .timestamp(Instant.now())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message("An unexpected error occurred. Please try again later.")
                                .code("INTERNAL_SERVER_ERROR")
                                .path(httpServletRequest.getRequestURI())
                                .build()
                );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException exception, HttpServletRequest httpServletRequest) {

        String message = "The request could not be completed.";
        String code = "DATA_INTEGRITY_VIOLATION";

        Throwable cause = exception;

        while (cause != null) {
            if (cause.getMessage() != null && cause.getMessage().contains("uk_users_username")) {
                message = "Username is already taken.";
                code = "USERNAME_ALREADY_EXISTS";
                break;
            }
            if (cause.getMessage() != null && cause.getMessage().contains("uk_users_email")) {
                code = "EMAIL_ALREADY_EXISTS";
                message = "Email is already registered.";
                break;
            }

            cause = cause.getCause();
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.builder()
                                .timestamp(Instant.now())
                                .status(HttpStatus.CONFLICT.value())
                                .message(message)
                                .code(code)
                                .path(httpServletRequest.getRequestURI())
                                .build()
                );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException exception, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .timestamp(Instant.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message(exception.getMessage())
                                .code("BAD_REQUEST")
                                .path(httpServletRequest.getRequestURI())
                                .build()
                );
    }
}
