package com.tempo.challenge.exception;

import javax.persistence.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {

    public RoleNotFoundException(String message) {
        super(message);
    }
}
