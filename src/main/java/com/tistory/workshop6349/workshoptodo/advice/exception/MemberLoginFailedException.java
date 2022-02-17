package com.tistory.workshop6349.workshoptodo.advice.exception;

public class MemberLoginFailedException extends RuntimeException {

    public MemberLoginFailedException() {
        super();
    }

    public MemberLoginFailedException(String message) {
        super(message);
    }

    public MemberLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
