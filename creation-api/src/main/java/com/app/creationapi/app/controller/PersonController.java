package com.app.creationapi.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.app.creationapi.app.controller.dto.PersonRequest;
import com.app.creationapi.domain.Person;
import com.app.creationapi.domain.PersonService;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor
@RequestMapping("/pessoas")
public class PersonController {
    private final PersonService personService;

    @PostMapping()
    public ResponseEntity<?> createPerson(@RequestBody PersonRequest request) {
        Person person = personService.createPerson(request);
        URI uri = URI.create("/pessoas/" + person.getId());
        return ResponseEntity.created(uri).body(person);
    }

    @GetMapping
    public ResponseEntity<List<?>> findPeople(
        @RequestParam(required = true, name = "t") String term) {
        List<Person> people = personService.filterPeopleFulltext(term);
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPerson(@PathVariable(required = true) String id) {
        return personService.findPerson(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/contagem-pessoas")
    public Long countPeople() {
        return personService.count();
    }
}
