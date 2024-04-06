package org.example.bookslibrary.mappers;

import org.example.bookslibrary.dtos.response.PatronResponseDto;
import org.example.bookslibrary.entities.Patron;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class PatronToPatronResponseDtoMapper implements Function<Patron, PatronResponseDto> {
    @Override
    public PatronResponseDto apply(Patron patron) {
        return PatronResponseDto.builder()
                .id(patron.getId())
                .name(patron.getName())
                .email(patron.getEmail())
                .phoneNumber(patron.getPhoneNumber())
                .contactInformation(patron.getContactInformation())
                .build();
    }
}
