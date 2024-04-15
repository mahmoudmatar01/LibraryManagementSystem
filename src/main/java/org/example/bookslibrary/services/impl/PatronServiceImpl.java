package org.example.bookslibrary.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookslibrary.dtos.request.PatronRequestDto;
import org.example.bookslibrary.dtos.response.PatronResponseDto;
import org.example.bookslibrary.entities.Patron;
import org.example.bookslibrary.exceptions.BadRequestException;
import org.example.bookslibrary.exceptions.NotFoundCustomException;
import org.example.bookslibrary.mappers.PatronRequestDtoToPatronMapper;
import org.example.bookslibrary.mappers.PatronToPatronResponseDtoMapper;
import org.example.bookslibrary.repositories.BorrowingRecordRepository;
import org.example.bookslibrary.repositories.PatronRepository;
import org.example.bookslibrary.services.PatronService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;
    private final PatronToPatronResponseDtoMapper patronToPatronResponseDtoMapper;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronRequestDtoToPatronMapper patronRequestDtoToPatronMapper;

    @Cacheable(cacheNames = "patrons")
    @Override
    public List<PatronResponseDto> getAllPatrons() {
        return patronRepository.findAll().stream()
                .sorted(Comparator.comparing(Patron::getId))
                .map(patronToPatronResponseDtoMapper)
                .toList();
    }

    @Cacheable(cacheNames = "patrons", key = "#id")
    @Override
    public PatronResponseDto getPatronById(Long id) {
        Patron patron=checkIfPatronExistedOrThrowException(id);
        return patronToPatronResponseDtoMapper.apply(patron);
    }

    @CacheEvict(cacheNames = "patrons", allEntries = true)
    @Override
    @Transactional
    public PatronResponseDto savePatron(PatronRequestDto patron) {
        Patron savedPatron=patronRequestDtoToPatronMapper.apply(patron);
        savedPatron=patronRepository.save(savedPatron);
        return patronToPatronResponseDtoMapper.apply(savedPatron);
    }

    @CacheEvict(cacheNames = "patrons", key = "#patronId", allEntries = true)
    @Override
    @Transactional
    public PatronResponseDto updatePatron(Long patronId, PatronRequestDto newPatronData) {
        Patron patron=checkIfPatronExistedOrThrowException(patronId);
        patron.setName(newPatronData.name());
        patron.setEmail(newPatronData.email());
        patron.setPhoneNumber(newPatronData.phoneNumber());
        patron=patronRepository.save(patron);
        return patronToPatronResponseDtoMapper.apply(patron);
    }

    @Override
    @CacheEvict(cacheNames = "patrons", allEntries = true)
    @Transactional(rollbackOn = ConstraintViolationException.class)
    public void deletePatronById(Long id) {
        if (borrowingRecordRepository.existsByPatronId(id)) {
            throw new BadRequestException("Patron cannot be deleted as it has associated borrowing records.");
        }
        Patron patron=checkIfPatronExistedOrThrowException(id);
        patronRepository.delete(patron);
    }
    // Helper Method
    private Patron checkIfPatronExistedOrThrowException(Long patronId){
        return patronRepository.findById(patronId).orElseThrow(
                ()->new NotFoundCustomException("Patron with id : "+patronId+" not founded!")
        );
    }
}
