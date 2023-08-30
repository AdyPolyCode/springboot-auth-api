package com.poly.schoolDataManager.payload.response;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ErrorPayload implements Serializable {
    private final HttpStatus statusCode;

    private final String statusMessage;

    private final String description;

    private final Object body;

    private ErrorPayload(Builder builder) {
        this.statusCode = builder.getStatusCode();
        this.statusMessage = builder.getStatusMessage();
        this.description = builder.getDescription();
        this.body = builder.getBody();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getDescription() {
        return description;
    }

    public Object getBody() {
        return body;
    }

    public static class Builder {
        private HttpStatus statusCode;
        private String statusMessage;
        private String description;
        private Object body;

        public HttpStatus getStatusCode() {
            return statusCode;
        }

        public Builder setStatusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public Builder setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Object getBody() {
            return body;
        }

        public Builder setBody(Object body) {
            this.body = body;
            return this;
        }

        public ErrorPayload build() {
            return new ErrorPayload(this);
        }
    }
}
