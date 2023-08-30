package com.poly.schoolDataManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.poly.schoolDataManager.payload.response.ErrorPayload;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = { NotFoundEntityException.class })
    public ResponseEntity<ErrorPayload> handleNotFoundEntity(NotFoundEntityException exception, WebRequest request) {
        ErrorPayload payload = new ErrorPayload.Builder()
                .setStatusCode(exception.getStatusCode())
                .setStatusMessage(exception.getMessage())
                .setDescription(request.getDescription(false))
                .build();

        return ResponseEntity.status(exception.getStatusCode()).body(payload);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorPayload> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(e -> {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            errors.put(field, message);
        });

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorPayload payload = new ErrorPayload.Builder()
                .setStatusCode(status)
                .setBody(errors)
                .build();

        return ResponseEntity.status(status).body(payload);
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<ErrorPayload> handleNotFoundUser(UserNotFoundException exception, WebRequest request) {
        ErrorPayload payload = new ErrorPayload.Builder()
                .setStatusCode(exception.getStatusCode())
                .setStatusMessage(exception.getMessage())
                .setDescription(request.getDescription(false))
                .build();

        return ResponseEntity.status(exception.getStatusCode()).body(payload);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorPayload> handleOther(Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorPayload payload = new ErrorPayload.Builder()
                .setStatusCode(status)
                .setStatusMessage(exception.getMessage())
                .setDescription(request.getDescription(false))
                .build();

        return ResponseEntity.status(status).body(payload);
    }
}
