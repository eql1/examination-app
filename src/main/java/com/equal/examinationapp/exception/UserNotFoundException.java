package com.equal.examinationapp.exception;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
