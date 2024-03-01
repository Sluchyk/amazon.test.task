package com.example.amazon.test.task.exceptions;

import lombok.NoArgsConstructor;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
