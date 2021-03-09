package com.tempo.challenge.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundExpcetion extends EntityNotFoundException {

    public UserNotFoundExpcetion(String message) {
        super(message);
    }
}
