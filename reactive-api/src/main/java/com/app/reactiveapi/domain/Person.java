package com.app.reactiveapi.domain;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Table(name = "pessoas")
public record Person(
    @Id UUID id,
    String apelido,
    String nome,
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate nascimento,
    Set<String> stack
) {}
