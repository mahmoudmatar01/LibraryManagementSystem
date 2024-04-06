package org.example.bookslibrary.repositories;

import org.example.bookslibrary.entities.LibraryAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<LibraryAdmin,Long> {
    Optional<LibraryAdmin> findByEmail(String email);
    boolean existsByEmail(String email);

}
