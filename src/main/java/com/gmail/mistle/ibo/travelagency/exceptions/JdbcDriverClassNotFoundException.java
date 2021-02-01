package com.gmail.mistle.ibo.travelagency.exceptions;

public class JdbcDriverClassNotFoundException extends RuntimeException {

    public JdbcDriverClassNotFoundException() {
        super();
    }

    public JdbcDriverClassNotFoundException(String message) {
        super(message);
    }

    public JdbcDriverClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
