package com.tempo.challenge.exception;

import javax.persistence.EntityNotFoundException;

public class TeamNotFoundException extends EntityNotFoundException {

    public TeamNotFoundException(String message) {
        super(message);
    }
}
