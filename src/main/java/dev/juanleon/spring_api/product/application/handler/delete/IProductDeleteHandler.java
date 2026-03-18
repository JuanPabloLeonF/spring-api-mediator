package dev.juanleon.spring_api.product.application.handler.delete;

import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;

import java.util.UUID;

public interface IProductDeleteHandler {
    public ResponseRequest deleteById(UUID id);
}
