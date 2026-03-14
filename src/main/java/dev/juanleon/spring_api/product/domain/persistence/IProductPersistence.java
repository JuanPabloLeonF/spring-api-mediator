package dev.juanleon.spring_api.product.domain.persistence;

import dev.juanleon.spring_api.product.domain.model.ProductModel;

import java.util.List;
import java.util.UUID;

public interface IProductPersistence {
    public String save(ProductModel product);
    public List<ProductModel> getAll();
    public ProductModel getById(UUID id);
}
