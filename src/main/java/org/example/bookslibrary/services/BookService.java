package org.example.bookslibrary.services;

import org.example.bookslibrary.dtos.request.BookRequestDto;
import org.example.bookslibrary.dtos.response.BookResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBooks();
    BookResponseDto getBookById(Long id);
    BookResponseDto saveBook(BookRequestDto book);
    BookResponseDto updateBook(Long bookId,BookRequestDto newBook);
    void deleteBookById(Long id);
    Integer uploadBook(MultipartFile file) throws IOException;

}
