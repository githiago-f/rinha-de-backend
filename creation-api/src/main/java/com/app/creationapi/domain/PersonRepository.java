package com.app.creationapi.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {
    List<Person> findByApelido(String apelido);

    @Query(value = "SELECT * FROM pessoas p WHERE p.searchable ilike :temp", nativeQuery = true)
    List<Person> findBySearchable(String temp);
}
