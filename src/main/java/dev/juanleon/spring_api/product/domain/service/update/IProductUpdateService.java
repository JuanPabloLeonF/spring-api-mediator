package dev.juanleon.spring_api.product.domain.service.update;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.common.utils.models.ResponseModel;

import java.util.UUID;

public interface IProductUpdateService {
    public ResponseModel updateById(UUID id, ProductModel productModel);
}
