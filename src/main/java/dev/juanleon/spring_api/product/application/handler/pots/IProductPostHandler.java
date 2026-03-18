package dev.juanleon.spring_api.product.application.handler.pots;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;

public interface IProductPostHandler {
    public ResponseRequest save(RequestProduct requestProduct);
}
