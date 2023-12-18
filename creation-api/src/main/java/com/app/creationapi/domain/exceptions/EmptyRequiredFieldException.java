package com.app.creationapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    code = HttpStatus.BAD_REQUEST, 
    reason = "Campos obrigatórios vazios"
)
public class EmptyRequiredFieldException extends IllegalArgumentException {
    public EmptyRequiredFieldException(String field) {
        super("O campo " + field + " não deve ser null ou vazio.");
    }
}
