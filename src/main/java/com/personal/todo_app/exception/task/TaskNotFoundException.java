package com.personal.todo_app.exception.task;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
