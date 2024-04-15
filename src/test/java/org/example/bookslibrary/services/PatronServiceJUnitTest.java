package org.example.bookslibrary.services;

import org.example.bookslibrary.dtos.request.PatronRequestDto;
import org.example.bookslibrary.dtos.response.PatronResponseDto;
import org.example.bookslibrary.entities.Patron;
import org.example.bookslibrary.exceptions.NotFoundCustomException;
import org.example.bookslibrary.mappers.PatronRequestDtoToPatronMapper;
import org.example.bookslibrary.mappers.PatronToPatronResponseDtoMapper;
import org.example.bookslibrary.repositories.PatronRepository;
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
public class PatronServiceJUnitTest {
    @MockBean
    private PatronRepository patronRepository;
    @Autowired
    private PatronService patronService;
    @Autowired
    private PatronToPatronResponseDtoMapper toPatronResponseDtoMapper;
    @Autowired
    private PatronRequestDtoToPatronMapper toPatronMapper;


    @Test
    public void whenFindAll_ReturnPatronsList(){
        // Mockup
        Patron patron=new Patron(1L,"Ahmed","Ahmed@mail.com","01128673348",new HashSet<>(),"Ahmed,Ahmed@mail.com,01128673348");
        Patron patron2=new Patron(2L,"Mahmoud","Mahmoud@mail.com","01111614941",new HashSet<>(),"Mahmoud,Mahmoud@mail.com,01111614941");

        List<Patron> patrons= Arrays.asList(patron,patron2);
        given(patronRepository.findAll()).willReturn(patrons);

        // Assertion Test
        assertThat(patronService.getAllPatrons())
                .contains(toPatronResponseDtoMapper.apply(patron),toPatronResponseDtoMapper.apply(patron2))
                .hasSize(2);
    }

    @Test
    public void whenGetById_PatronShouldBeFound(){
        // Mockup
        Patron patron=new Patron(1L,"Ahmed","Ahmed@mail.com","01128673348",new HashSet<>(),"Ahmed,Ahmed@mail.com,01128673348");
        given(patronRepository.findById(anyLong())).willReturn(Optional.of(patron));

        // Assertion Test
        PatronResponseDto result=patronService.getPatronById(1L);
        assertThat(result.name())
                .containsIgnoringCase("Ahmed");
    }

    @Test
    public void whenInvalidId_PatronShouldNotBeFound(){
        given(patronRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(NotFoundCustomException.class, () -> patronService.getPatronById(1L));
    }

    @Test
    public void whenSavePatron_PatronShouldBeSavedAndReturnedAsResponseDto() {
        PatronRequestDto patronRequestDto = new PatronRequestDto("Ahmed", "Ahmed@mail.com", "01128673348");

        // Mockup
        Patron savedPatron = toPatronMapper.apply(patronRequestDto);
        given(patronRepository.save(savedPatron)).willReturn(savedPatron);

        var responseDto = patronService.savePatron(patronRequestDto);

        // Assertion Test
        assertThat(responseDto.name()).isEqualTo(patronRequestDto.name());
        assertThat(responseDto.email()).isEqualTo(patronRequestDto.email());
    }

    @Test
    public void whenUpdatePatron_PatronShouldBeUpdatedAndReturnedAsResponseDto(){
        // Mock data
        Long patronId = 1L;
        PatronRequestDto newPatron = new PatronRequestDto("Mahmoud", "Mahmoud@mail.com", "01111614941");
        Patron patron=new Patron(1L,"Ahmed","Ahmed@mail.com","01128673348",new HashSet<>(),"Ahmed,Ahmed@mail.com,01128673348");
        given(patronRepository.findById(patronId)).willReturn(Optional.of(patron));
        given(patronRepository.save(patron)).willReturn(patron);

        var responseDto = patronService.updatePatron(patronId, newPatron);

        // Assertions
        assertThat(responseDto.name()).isEqualTo(newPatron.name());
        assertThat(responseDto.email()).isEqualTo(newPatron.email());
    }

}
