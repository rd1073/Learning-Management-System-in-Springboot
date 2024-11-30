package com.example.lms.Exceptions;

public class DuplicateKeyException extends RuntimeException {
    
    private String message;

    public DuplicateKeyException() {
        super();
    }

    public DuplicateKeyException(String message) {
        super(message);
        this.message = message;
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
