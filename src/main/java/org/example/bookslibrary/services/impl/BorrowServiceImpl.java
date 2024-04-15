package org.example.bookslibrary.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.entities.Book;
import org.example.bookslibrary.entities.BorrowingRecord;
import org.example.bookslibrary.entities.Patron;
import org.example.bookslibrary.exceptions.BookAlreadyBorrowedException;
import org.example.bookslibrary.exceptions.NotFoundCustomException;
import org.example.bookslibrary.repositories.BookRepository;
import org.example.bookslibrary.repositories.BorrowingRecordRepository;
import org.example.bookslibrary.repositories.PatronRepository;
import org.example.bookslibrary.services.BorrowService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;
    @Override
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = checkBookIsExistedOrThrowException(bookId);
        Patron patron = checkPatronIsExistedOrThrowException(patronId);

        // Check if book is already borrowed
        if (borrowingRecordRepository.existsByBookIdAndPatronId(bookId,patronId)) {
            throw new BookAlreadyBorrowedException("Book with ID " + bookId + " is already borrowed by "+patron.getName());
        }

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .borrowDate(LocalDateTime.now())
                .build();

        borrowingRecord=borrowingRecordRepository.save(borrowingRecord);
        return borrowingRecord;
    }

    @Override
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Book book = checkBookIsExistedOrThrowException(bookId);
        Patron patron = checkPatronIsExistedOrThrowException(patronId);
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(book.getId(), patron.getId()).orElseThrow(
                ()->new NotFoundCustomException("This patron with id "+patronId+" has not borrowed this book with id "+bookId)
        );
        borrowingRecord.setReturnDate(LocalDateTime.now());
        borrowingRecord=borrowingRecordRepository.save(borrowingRecord);
        return borrowingRecord;
    }

    // Helper Methods
    private Book checkBookIsExistedOrThrowException(Long bookId){
        return  bookRepository.findById(bookId).orElseThrow(
                ()->new NotFoundCustomException("Book with ID " + bookId + " not found.")
        );
    }
    private Patron checkPatronIsExistedOrThrowException(Long patronId){
        return  patronRepository.findById(patronId).orElseThrow(
                ()->new NotFoundCustomException("Patron with ID " + patronId + " not found.")
        );
    }
}
