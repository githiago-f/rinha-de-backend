package com.app.reactiveapi.app.controller.dto;

import java.util.Set;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import com.app.reactiveapi.domain.Person;
import com.app.reactiveapi.domain.exceptions.EmptyRequiredFieldException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public record PersonRequest(
    String apelido,
    String nome,
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate nascimento,
    List<String> stack
) {
    public Person toEntity() {
        if(apelido() == null || apelido().isEmpty()) {
            throw new EmptyRequiredFieldException("apelido");
        }
        if(nome() == null || nome().isEmpty()) {
            throw new EmptyRequiredFieldException("nome");
        }
        List<String> stackHolder = stack();
        if(stack() == null) {
            stackHolder = new ArrayList<>();
        }
        return new Person(null, apelido, nome, nascimento, Set.copyOf(stackHolder));
    }
}
