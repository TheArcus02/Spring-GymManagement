package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>, SearchableRepository<Trainer> {
    Iterable<Trainer> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}
