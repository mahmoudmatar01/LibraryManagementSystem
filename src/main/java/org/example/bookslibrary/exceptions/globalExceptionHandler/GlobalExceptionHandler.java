package org.example.bookslibrary.exceptions.globalExceptionHandler;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.exceptions.BadRequestException;
import org.example.bookslibrary.exceptions.BookAlreadyBorrowedException;
import org.example.bookslibrary.exceptions.DelegatedAuthenticationException;
import org.example.bookslibrary.exceptions.NotFoundCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationExceptions(ConstraintViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = NotFoundCustomException.class)
    public ResponseEntity<?> notFoundException(NotFoundCustomException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
    @ExceptionHandler(value = BookAlreadyBorrowedException.class)
    public ResponseEntity<?> bookAlreadyBookedException(BookAlreadyBorrowedException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }


    @ExceptionHandler(value = DelegatedAuthenticationException.class)
    public ResponseEntity<?> delegatedAuthenticationException(DelegatedAuthenticationException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }
}
