package org.example.bookslibrary.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.PatronRequestDto;
import org.example.bookslibrary.services.PatronService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;

    @GetMapping
    public ResponseEntity<?> getPatrons() {
        var response=patronService.getAllPatrons();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id) {
        var response=patronService.getPatronById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> savePatron(@Valid @RequestBody PatronRequestDto requestDto) {
        var response=patronService.savePatron(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id,@Valid @RequestBody PatronRequestDto requestDto) {
        var response=patronService.updatePatron(id,requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePatron(@PathVariable Long id) {
        patronService.deletePatronById(id);
        return ResponseEntity.ok("patron removed successfully ");
    }
}
