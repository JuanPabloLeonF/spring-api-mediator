package dev.juanleon.spring_api.product.application.handler.update;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;

import java.util.UUID;

public interface IProductUpdateHandler {
    public ResponseRequest updateById(UUID id, RequestProduct requestProduct);
}
