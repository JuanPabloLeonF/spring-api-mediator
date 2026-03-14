package dev.juanleon.spring_api.product.domain.service;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.model.ResponseModel;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    public ResponseModel save(ProductModel product);
    public List<ProductModel> getAll();
    public ProductModel getById(UUID id);
}
