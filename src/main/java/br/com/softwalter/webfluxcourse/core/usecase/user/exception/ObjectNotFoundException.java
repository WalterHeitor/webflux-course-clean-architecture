package br.com.softwalter.webfluxcourse.core.usecase.user.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
