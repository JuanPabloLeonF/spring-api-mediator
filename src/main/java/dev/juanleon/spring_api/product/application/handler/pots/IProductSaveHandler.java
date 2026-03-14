package dev.juanleon.spring_api.product.application.handler.pots;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseRequest;

public interface IProductSaveHandler {
    public ResponseRequest save(RequestProduct requestProduct);
}
