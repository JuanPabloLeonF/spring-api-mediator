package dev.juanleon.spring_api.product.application.handler.gets;

import dev.juanleon.spring_api.product.application.dto.ResponseProduct;

import java.util.List;
import java.util.UUID;

public interface IProductGetHandler {
    public List<ResponseProduct> getAll();
    public ResponseProduct getById(UUID id);
}
