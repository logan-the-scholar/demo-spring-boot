package com.study.demo.common.exception.classes;

public class EmailAlreadyExistsException extends ExceptionWrapper {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
