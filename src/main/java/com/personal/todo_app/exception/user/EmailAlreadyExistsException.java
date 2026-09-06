package com.personal.todo_app.exception.user;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super("An account with this email already exists.");
    }
}
