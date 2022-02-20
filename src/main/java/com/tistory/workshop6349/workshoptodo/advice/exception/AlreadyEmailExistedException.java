package com.tistory.workshop6349.workshoptodo.advice.exception;

public class AlreadyEmailExistedException extends RuntimeException {

    public AlreadyEmailExistedException() {
        super();
    }

    public AlreadyEmailExistedException(String message) {
        super(message);
    }

    public AlreadyEmailExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
