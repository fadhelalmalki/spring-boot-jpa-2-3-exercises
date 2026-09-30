package org.fadhel.springbootjpa1exercise.api;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
