package org.example.bookslibrary.mappers;

import org.example.bookslibrary.dtos.request.PatronRequestDto;
import org.example.bookslibrary.entities.Patron;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class PatronRequestDtoToPatronMapper implements Function<PatronRequestDto, Patron> {
    @Override
    public Patron apply(PatronRequestDto patronRequestDto) {
        return Patron.builder()
                .name(patronRequestDto.name())
                .email(patronRequestDto.email())
                .phoneNumber(patronRequestDto.phoneNumber())
                .build();
    }
}
