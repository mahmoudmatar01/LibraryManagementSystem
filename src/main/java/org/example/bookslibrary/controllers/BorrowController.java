package org.example.bookslibrary.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.entities.BorrowingRecord;
import org.example.bookslibrary.services.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
            var borrowingRecord = borrowService.borrowBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable  Long bookId, @PathVariable Long patronId ) {
        var borrowingRecord = borrowService.returnBook(bookId,patronId);
        return ResponseEntity.ok(borrowingRecord);
    }
}
