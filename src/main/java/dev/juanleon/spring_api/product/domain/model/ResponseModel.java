package dev.juanleon.spring_api.product.domain.model;

import java.time.LocalDateTime;

public record ResponseModel(String message, LocalDateTime dateTime) {
    public ResponseModel(String message) {
        this(message, LocalDateTime.now());
    }
}
