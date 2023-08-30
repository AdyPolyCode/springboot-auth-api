package com.poly.schoolDataManager.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
        this.statusCode = HttpStatus.NOT_FOUND;
    }
}
