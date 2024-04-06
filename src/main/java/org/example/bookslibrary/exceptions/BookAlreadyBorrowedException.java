package org.example.bookslibrary.exceptions;

public class BookAlreadyBorrowedException extends RuntimeException{
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}
