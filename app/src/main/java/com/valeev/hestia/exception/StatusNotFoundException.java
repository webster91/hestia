package com.valeev.hestia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Статус не найден")
public class StatusNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StatusNotFoundException() {
        super();
    }
}
