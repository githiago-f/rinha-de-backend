package com.app.reactiveapi.domain;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.reactiveapi.app.controller.dto.PersonRequest;
import com.app.reactiveapi.domain.exceptions.NicknameTakenException;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> createPerson(PersonRequest personRequest) {
        return personRepository.findByApelido(personRequest.apelido())
            .collectList()
            .flatMap(people -> {
                if(people.size() > 0) {
                    throw new NicknameTakenException();
                }
                return personRepository.save(personRequest.toEntity());
            });
    }

    public Mono<Person> findPerson(String id) {
        UUID uuid = UUID.fromString(id);
        return personRepository.findById(uuid);
    }

    public Flux<Person> filterPeopleFulltext(String term) {
        return personRepository.findBySearchable('%' + term + '%');
    }

    public Mono<Long> count() {
        return personRepository.count();
    }

}
