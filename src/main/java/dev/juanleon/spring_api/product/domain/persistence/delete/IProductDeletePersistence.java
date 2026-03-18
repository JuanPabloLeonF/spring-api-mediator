package dev.juanleon.spring_api.product.domain.persistence.delete;


import dev.juanleon.spring_api.product.domain.model.ProductModel;

import java.util.UUID;

public interface IProductDeletePersistence {
    public ProductModel deleteById(UUID id);
}
