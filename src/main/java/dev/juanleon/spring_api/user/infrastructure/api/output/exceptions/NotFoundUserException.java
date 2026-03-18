package dev.juanleon.spring_api.user.infrastructure.api.output.exceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String message) {
        super(message);
    }
}
