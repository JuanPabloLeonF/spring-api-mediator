package dev.juanleon.spring_api.product.domain.model;

import java.util.UUID;

public record ProductModel(UUID id, String name, Double price) {
}
