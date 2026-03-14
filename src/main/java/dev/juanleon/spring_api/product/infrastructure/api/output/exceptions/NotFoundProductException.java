package dev.juanleon.spring_api.product.infrastructure.api.output.exceptions;

public class NotFoundProductException  extends RuntimeException {

    public NotFoundProductException(String message) {
        super(message);
    }
}
