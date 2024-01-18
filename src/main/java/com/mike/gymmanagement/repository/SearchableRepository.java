package com.mike.gymmanagement.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface SearchableRepository<T> extends QueryByExampleExecutor<T> {
    Iterable<T> findByNameContainingIgnoreCase(@Param("name") String name);
}
