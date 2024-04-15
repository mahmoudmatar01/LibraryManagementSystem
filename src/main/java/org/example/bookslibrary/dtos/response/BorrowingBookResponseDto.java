package org.example.bookslibrary.dtos.response;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BorrowingBookResponseDto(
         Long id,
         Long bookId,
         String bookTitle,
         Long patronId,
         String patronName,
         LocalDateTime borrowDate,
         LocalDateTime returnDate
) {
}
