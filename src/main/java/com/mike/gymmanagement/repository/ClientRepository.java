package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.model.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>, SearchableRepository<Client> {
    Iterable<Client> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}