package com.example.amazon.test.task.exceptions;

public class UniqueUserEmailException extends RuntimeException {
    public UniqueUserEmailException(String message){
        super(message);
    }
}
