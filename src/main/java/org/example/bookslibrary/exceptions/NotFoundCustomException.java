package org.example.bookslibrary.exceptions;

public class NotFoundCustomException extends RuntimeException {
    public NotFoundCustomException(String message) {
        super(message);
    }
}
