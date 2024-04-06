package org.example.bookslibrary.services;

import org.example.bookslibrary.entities.BorrowingRecord;

public interface BorrowService {
    BorrowingRecord borrowBook(Long bookId, Long patronId);
    BorrowingRecord returnBook(Long bookId, Long patronId);
}
