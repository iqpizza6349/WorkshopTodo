package com.tistory.workshop6349.workshoptodo.advice.exception;

public class AlreadyPostExistedException extends RuntimeException {

    public AlreadyPostExistedException() {
        super();
    }

    public AlreadyPostExistedException(String message) {
        super(message);
    }

    public AlreadyPostExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
