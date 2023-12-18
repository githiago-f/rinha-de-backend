package com.app.reactiveapi.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.app.reactiveapi.app.controller.dto.PersonRequest;
import com.app.reactiveapi.domain.Person;
import com.app.reactiveapi.domain.PersonService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/pessoas")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public Mono<ResponseEntity<?>> createPerson(@RequestBody PersonRequest request) {
        return personService.createPerson(request)
            .map(p -> URI.create("/pessoas/" + p.id()))
            .map(uri -> ResponseEntity.created(uri).build());
    }

    @GetMapping
    public Mono<ResponseEntity<List<?>>> findPeople(
        @RequestParam(required = true, name = "t") String term) {
        return personService.filterPeopleFulltext(term).collectList()
            .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> findPerson(
        @PathVariable(required = true) String id) {
        return personService.findPerson(id)
            .map(ResponseEntity::ok)
            .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping("/contagem-pessoas")
    public Mono<Long> countPeople() {
        return personService.count();
    }
}
