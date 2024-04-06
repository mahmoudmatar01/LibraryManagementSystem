package org.example.bookslibrary.repositories;

import org.example.bookslibrary.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
}
