package com.app.creationapi.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.creationapi.app.controller.dto.PersonRequest;
import com.app.creationapi.domain.exceptions.NicknameTakenException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person createPerson(PersonRequest personRequest) {
        List<Person> people = personRepository.findByApelido(personRequest.apelido());
        if(people.size() > 0) {
            throw new NicknameTakenException();
        }
        return personRepository.save(personRequest.toEntity());
    }

    public Optional<Person> findPerson(String id) {
        UUID uuid = UUID.fromString(id);
        return personRepository.findById(uuid);
    }

    public List<Person> filterPeopleFulltext(String term) {
        return personRepository.findBySearchable('%' + term + '%');
    }

    public Long count() {
        return personRepository.count();
    }

}
