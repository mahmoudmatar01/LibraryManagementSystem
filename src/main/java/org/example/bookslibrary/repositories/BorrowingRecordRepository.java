package org.example.bookslibrary.repositories;

import org.example.bookslibrary.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
    boolean existsByBookIdAndPatronId(Long bookId,Long patronId);
    boolean existsByBookId(Long bookId);
    boolean existsByPatronId(Long patronId);
    Optional<BorrowingRecord> findByBookIdAndPatronId(Long bookId, Long patronId);
}
