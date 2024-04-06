package org.example.bookslibrary.mappers;

import org.example.bookslibrary.dtos.request.BookRequestDto;
import org.example.bookslibrary.entities.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class BookRequestDtoToBookMapper implements Function<BookRequestDto, Book> {
    @Override
    public Book apply(BookRequestDto bookRequestDto) {
        return Book.builder()
                .title(bookRequestDto.title())
                .author(bookRequestDto.author())
                .publicationYear(bookRequestDto.publicationYear())
                .isbn(bookRequestDto.isbn())
                .build();
    }
}
