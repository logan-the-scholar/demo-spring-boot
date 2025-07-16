package com.study.demo.common.exception.classes;

public class PasswordDontMatchException extends RuntimeException{
    public PasswordDontMatchException(String message) {
        super(message);
    }
}
