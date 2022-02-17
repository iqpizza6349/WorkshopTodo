package com.tistory.workshop6349.workshoptodo.advice.exception;

public class AuthenticationEntrypointException extends RuntimeException {

    public AuthenticationEntrypointException() {
        super();
    }

    public AuthenticationEntrypointException(String message) {
        super(message);
    }

    public AuthenticationEntrypointException(String message, Throwable cause) {
        super(message, cause);
    }
}
