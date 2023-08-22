package com.poly.schoolDataManager.exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    String message;

    HttpStatus statusCode;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
