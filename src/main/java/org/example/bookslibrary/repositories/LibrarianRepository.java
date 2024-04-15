package org.example.bookslibrary.repositories;

import org.example.bookslibrary.entities.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian,Long> {
    Optional<Librarian> findByEmail(String email);
    boolean existsByEmail(String email);

}
