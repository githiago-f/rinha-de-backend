package com.app.creationapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class NicknameTakenException extends IllegalArgumentException {
    public NicknameTakenException() {
        super("Apelido já em uso");
    }
}
