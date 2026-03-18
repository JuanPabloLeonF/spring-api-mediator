package dev.juanleon.spring_api.product.domain.persistence.gets;

import dev.juanleon.spring_api.product.domain.model.ProductModel;

import java.util.List;
import java.util.UUID;

public interface IProductGetsPersistence {
    public List<ProductModel> getAll();
    public ProductModel getById(UUID id);
}
