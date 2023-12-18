package com.app.reactiveapi.domain;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, UUID> {
    Flux<Person> findByApelido(String apelido);

    @Query("SELECT * FROM pessoas p WHERE p.searchable ilike :temp")
    Flux<Person> findBySearchable(String temp);
}
