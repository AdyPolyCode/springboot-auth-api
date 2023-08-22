package com.poly.schoolDataManager.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundEntityException extends BaseException {
    public NotFoundEntityException(String message) {
        super(message);
        this.message = message;
        this.statusCode = HttpStatus.NOT_FOUND;
    }
}
