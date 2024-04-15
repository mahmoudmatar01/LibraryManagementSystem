package org.example.bookslibrary.services;

import org.example.bookslibrary.dtos.response.BorrowingBookResponseDto;
import org.example.bookslibrary.entities.BorrowingRecord;

public interface BorrowService {
    BorrowingBookResponseDto borrowBook(Long bookId, Long patronId);
    BorrowingBookResponseDto returnBook(Long bookId, Long patronId);
}
