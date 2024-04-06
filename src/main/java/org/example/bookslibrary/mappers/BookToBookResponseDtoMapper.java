package org.example.bookslibrary.mappers;

import org.example.bookslibrary.dtos.response.BookResponseDto;
import org.example.bookslibrary.entities.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class BookToBookResponseDtoMapper implements Function<Book, BookResponseDto> {
    @Override
    public BookResponseDto apply(Book book) {
        return BookResponseDto.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .isbn(book.getIsbn())
                .build();
    }
}
