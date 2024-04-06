package org.example.bookslibrary.dtos.response;

import lombok.Builder;

@Builder
public record BookResponseDto(
        Long bookId,
        String title,
        String author,
        int publicationYear,
        String isbn
) {
}
