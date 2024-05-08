package org.example.bookslibrary.services;

import org.example.bookslibrary.dtos.request.BookRequestDto;
import org.example.bookslibrary.dtos.response.BookResponseDto;
import org.example.bookslibrary.entities.Book;
import org.example.bookslibrary.exceptions.NotFoundCustomException;
import org.example.bookslibrary.mappers.BookRequestDtoToBookMapper;
import org.example.bookslibrary.mappers.BookToBookResponseDtoMapper;
import org.example.bookslibrary.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class BookServiceJUnitTest {

    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookToBookResponseDtoMapper toBookResponseDtoMapper;
    @Autowired
    private BookRequestDtoToBookMapper toBookMapper;


    @Test
    public void whenFindAll_ReturnBooksList(){
        // Mockup
        Book book1=new Book(1L,"First Book","First Book Author",11,"skhfdkjsdfhjkhjh",new HashSet<>());
        Book book2=new Book(2L,"Second Book","Second Book Author",22,"lkdsjflksdnxmcnkl",new HashSet<>());

        List<Book> bookList= Arrays.asList(book1,book2);
        given(bookRepository.findAll()).willReturn(bookList);

        // Assertion Test
        assertThat(bookService.getAllBooks())
                .contains(toBookResponseDtoMapper.apply(book1),toBookResponseDtoMapper.apply(book2))
                .hasSize(2);
    }

    @Test
    public void whenGetById_BookShouldBeFound(){
        // Mockup
        Book book1=new Book(1L,"First Book","First Book Author",11,"skhfdkjsdfhjkhjh",new HashSet<>());
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(book1));

        // Assertion Test
        BookResponseDto result=bookService.getBookById(1L);
        assertThat(result.title())
                .containsIgnoringCase("First Book");
    }

    @Test
    public void whenInvalidId_BookShouldNotBeFound(){
        given(bookRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(NotFoundCustomException.class, () -> bookService.getBookById(1L));
    }

    @Test
    public void whenSaveBook_BookShouldBeSavedAndReturnedAsResponseDto() {
        BookRequestDto bookRequestDto = new BookRequestDto("New Book", "New Author", 2023, "isbn-1234");
        // Mockup
        Book savedBook = toBookMapper.apply(bookRequestDto);
        given(bookRepository.save(savedBook)).willReturn(savedBook);

        BookResponseDto responseDto = bookService.saveBook(bookRequestDto);

        // Assertion Test
        assertThat(responseDto.title()).isEqualTo(bookRequestDto.title());
        assertThat(responseDto.author()).isEqualTo(bookRequestDto.author());
    }

    @Test
    public void whenUpdateBook_BookShouldBeUpdatedAndReturnedAsResponseDto(){
        // Mock data
        Long bookId = 1L;
        BookRequestDto newBook = new BookRequestDto("Updated Title", "Updated Author", 2024, "new-isbn");
        Book book = new Book(bookId, "Old Book Title", "Old Book Author", 2022, "old-isbn",new HashSet<>());
        given(bookRepository.findById(bookId)).willReturn(Optional.of(book));
        given(bookRepository.save(book)).willReturn(book);

        BookResponseDto responseDto = bookService.updateBook(bookId, newBook);

        // Assertions
        assertThat(responseDto.title()).isEqualTo(newBook.title());
        assertThat(responseDto.author()).isEqualTo(newBook.author());
    }

}
