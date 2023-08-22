package com.poly.schoolDataManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.poly.schoolDataManager.payload.response.ErrorPayload;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = { NotFoundEntityException.class })
    public ResponseEntity<ErrorPayload> handleNotFound(NotFoundEntityException exception, WebRequest request) {
        ErrorPayload payload = new ErrorPayload.Builder()
                .setStatusCode(exception.getStatusCode())
                .setStatusMessage(exception.getMessage())
                .setDescription(request.getDescription(false))
                .build();

        return new ResponseEntity<>(payload, exception.getStatusCode());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorPayload> handleOther(Exception exception, WebRequest request) {
        ErrorPayload payload = new ErrorPayload.Builder()
                .setStatusCode(HttpStatus.BAD_REQUEST)
                .setStatusMessage(exception.getMessage())
                .setDescription(request.getDescription(false))
                .build();

        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }
}
