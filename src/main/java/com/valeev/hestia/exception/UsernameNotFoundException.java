package com.valeev.hestia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Пользователь не найден")
public class UsernameNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException() {
        super();
    }
}
