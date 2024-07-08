package org.example.bookslibrary.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.BookRequestDto;
import org.example.bookslibrary.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks() {
        var response=bookService.getAllBooks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        var response=bookService.getBookById(bookId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        var response=bookService.saveBook(bookRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId,@Valid @RequestBody BookRequestDto bookRequestDto) {
        var response=bookService.updateBook(bookId,bookRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> removeBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book removed successfully ");
    }

    @PostMapping(value = "/upload",consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadBook(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("List of books with size "+bookService.uploadBook(file)+" saved in DB successfully");
    }
}


