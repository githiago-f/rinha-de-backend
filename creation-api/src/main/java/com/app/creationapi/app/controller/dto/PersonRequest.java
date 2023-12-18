package com.app.creationapi.app.controller.dto;

import java.util.List;
import java.util.Set;

import com.app.creationapi.domain.Person;
import com.app.creationapi.domain.exceptions.EmptyRequiredFieldException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.util.Date;

public record PersonRequest(
    String apelido, 
    String nome, 
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    Date nascimento,
    List<String> stack
) {
    public Person toEntity() {
        if(apelido() == null || apelido().isEmpty()) {
            throw new EmptyRequiredFieldException("apelido");
        }
        if(nome() == null || nome().isEmpty()) {
            throw new EmptyRequiredFieldException("nome");
        }
        Person.PersonBuilder builder = Person.builder()
            .apelido(apelido())
            .nascimento(nascimento())
            .nome(nome());
        if(stack() != null) {
            builder.stack(Set.copyOf(stack()));
        }
        return builder.build();
    }
}
