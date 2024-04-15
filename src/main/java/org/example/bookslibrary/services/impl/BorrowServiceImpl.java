package org.example.bookslibrary.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.response.BorrowingBookResponseDto;
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
@Transactional
public class BorrowServiceImpl implements BorrowService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Override
    public BorrowingBookResponseDto borrowBook(Long bookId, Long patronId) {
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

        // Saving borrowing book into database
        borrowingRecord=borrowingRecordRepository.save(borrowingRecord);

        return mapBorrowingBookToBorrowingBookResponseDto(borrowingRecord);
    }

    @Override
    public BorrowingBookResponseDto returnBook(Long bookId, Long patronId) {
        Book book = checkBookIsExistedOrThrowException(bookId);
        Patron patron = checkPatronIsExistedOrThrowException(patronId);

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(book.getId(), patron.getId()).orElseThrow(
                ()->new NotFoundCustomException("This patron with id "+patronId+" has not borrowed this book with id "+bookId)
        );
        borrowingRecord.setReturnDate(LocalDateTime.now());

        borrowingRecord=borrowingRecordRepository.save(borrowingRecord);

        return mapBorrowingBookToBorrowingBookResponseDto(borrowingRecord);
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

    private BorrowingBookResponseDto mapBorrowingBookToBorrowingBookResponseDto(BorrowingRecord borrowingRecord){
        return BorrowingBookResponseDto.builder()
                .id(borrowingRecord.getId())
                .bookId(borrowingRecord.getBook().getId())
                .bookTitle(borrowingRecord.getBook().getTitle())
                .patronId(borrowingRecord.getPatron().getId())
                .patronName(borrowingRecord.getPatron().getName())
                .borrowDate(borrowingRecord.getBorrowDate())
                .returnDate(borrowingRecord.getReturnDate())
                .build();
    }
}
