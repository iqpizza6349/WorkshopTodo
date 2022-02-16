package com.tistory.workshop6349.workshoptodo.advice.exception;

public class AlreadyUsernameExistedException extends RuntimeException {

    public AlreadyUsernameExistedException() {
        super();
    }

    public AlreadyUsernameExistedException(String message) {
        super(message);
    }

    public AlreadyUsernameExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
