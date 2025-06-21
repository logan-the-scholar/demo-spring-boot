package com.study.demo.common.exception;

import com.study.demo.common.exception.classes.EmailAlreadyExistsException;
import com.study.demo.common.exception.classes.EmptyResourcesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailExists(EmailAlreadyExistsException exception) {
        return new ResponseEntity<>(format(exception), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyResourcesException.class)
    public ResponseEntity<?> emptyResources(EmptyResourcesException exception) {
        return new ResponseEntity<>(format(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler()
//    public ResponseEntity<?> common() {
//        return new ResponseEntity<>("jaja",HttpStatus.NOT_FOUND);
//    }

    private Map<String, String> format(RuntimeException exception) {
        return Map.of("message", exception.getMessage());
    }
}
