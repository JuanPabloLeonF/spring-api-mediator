package dev.juanleon.spring_api.product.domain.persistence.update;

import dev.juanleon.spring_api.product.domain.model.ProductModel;

import java.util.UUID;

public interface IProductUpdatePersistence {
    public String updateById(UUID id, ProductModel productModel);
}
