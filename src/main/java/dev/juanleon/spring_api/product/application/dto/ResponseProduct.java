package dev.juanleon.spring_api.product.application.dto;

import java.util.UUID;

public class ResponseProduct {

    private UUID id;
    private String name;
    private Double price;

    public ResponseProduct() {
    }

    public ResponseProduct(UUID id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
