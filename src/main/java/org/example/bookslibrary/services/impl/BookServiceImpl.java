package org.example.bookslibrary.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.BookRequestDto;
import org.example.bookslibrary.dtos.response.BookResponseDto;
import org.example.bookslibrary.entities.Book;
import org.example.bookslibrary.exceptions.BadRequestException;
import org.example.bookslibrary.exceptions.NotFoundCustomException;
import org.example.bookslibrary.mappers.BookRequestDtoToBookMapper;
import org.example.bookslibrary.mappers.BookToBookResponseDtoMapper;
import org.example.bookslibrary.repositories.BookRepository;
import org.example.bookslibrary.repositories.BorrowingRecordRepository;
import org.example.bookslibrary.services.BookService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookToBookResponseDtoMapper bookToBookResponseDtoMapper;
    private final BookRequestDtoToBookMapper bookRequestDtoToBookMapper;


    @Cacheable(cacheNames = "books")
    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .sorted(Comparator.comparing(Book::getId))
                .map(bookToBookResponseDtoMapper)
                .toList();
    }

    // Cache by book ID
    @Cacheable(cacheNames = "books", key = "#id")
    @Override
    public BookResponseDto getBookById(Long id) {
        Book book= checkIfBookExistedOrThrowException(id);
        return bookToBookResponseDtoMapper.apply(book);
    }

    // Evict all books cache after save
    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    @Transactional
    public BookResponseDto saveBook(BookRequestDto book) {
        Book savedBook=bookRequestDtoToBookMapper.apply(book);
        savedBook=bookRepository.save(savedBook);
        return bookToBookResponseDtoMapper.apply(savedBook);
    }

    @CacheEvict(cacheNames = "books", key = "#bookId", allEntries = true)
    @Override
    @Transactional
    public BookResponseDto updateBook(Long bookId, BookRequestDto newBook) {
        Book book= checkIfBookExistedOrThrowException(bookId);
        book.setAuthor(newBook.author());
        book.setTitle(newBook.title());
        book.setPublicationYear(newBook.publicationYear());
        book.setIsbn(newBook.isbn());
        book=bookRepository.save(book);
        return bookToBookResponseDtoMapper.apply(book);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "books", allEntries = true)
    public void deleteBookById(Long id) {
        Book book= checkIfBookExistedOrThrowException(id);
        if (borrowingRecordRepository.existsByBookId(id)) {
            throw new BadRequestException("Book cannot be deleted as it has associated borrowing records.");
        }
        bookRepository.delete(book);
    }

    // Helper Method
    private Book checkIfBookExistedOrThrowException(Long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()->new NotFoundCustomException("Book with id : "+bookId+" not found"));
    }
}
