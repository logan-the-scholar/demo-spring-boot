package com.study.demo.common.exception;

public class PasswordDontMatchException extends RuntimeException{
    public PasswordDontMatchException(String message) {
        super(message);
    }
}
