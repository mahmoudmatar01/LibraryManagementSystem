package org.example.bookslibrary.exceptions;

public class DelegatedAuthenticationException extends RuntimeException{
    public DelegatedAuthenticationException(String message) {
        super(message);
    }
}
