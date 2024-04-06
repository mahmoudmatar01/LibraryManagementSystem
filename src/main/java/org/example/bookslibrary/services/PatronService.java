package org.example.bookslibrary.services;

import org.example.bookslibrary.dtos.request.PatronRequestDto;
import org.example.bookslibrary.dtos.response.PatronResponseDto;

import java.util.List;

public interface PatronService {
    List<PatronResponseDto> getAllPatrons();
    PatronResponseDto getPatronById(Long id);
    PatronResponseDto savePatron(PatronRequestDto patron);
    PatronResponseDto updatePatron(Long patronId,PatronRequestDto newPatronData);
    void deletePatronById(Long id);
}
