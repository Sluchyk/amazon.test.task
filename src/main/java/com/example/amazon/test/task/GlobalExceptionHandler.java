package com.example.amazon.test.task;

import com.example.amazon.test.task.exceptions.NotFoundException;
import com.mongodb.MongoWriteException;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<String> handleDuplicateEmailException(MongoWriteException ex) {
        logger.info(ex.getMessage());
        return new ResponseEntity<>("User with such email already exists", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        logger.info(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        logger.info(ex.getMessage());
        return new ResponseEntity<>("Cant read file, incorrect path", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        logger.info(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        logger.info(ex.getMessage());
        return new ResponseEntity<>("Cant find user.User with such email doesnt exist or wrong password", HttpStatus.BAD_REQUEST);
    }

}
