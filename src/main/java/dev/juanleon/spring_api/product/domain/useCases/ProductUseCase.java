package dev.juanleon.spring_api.product.domain.useCases;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.model.ResponseModel;
import dev.juanleon.spring_api.product.domain.persistence.IProductPersistence;
import dev.juanleon.spring_api.product.domain.service.IProductService;

import java.util.List;
import java.util.UUID;

public class ProductUseCase implements IProductService {

    private final IProductPersistence iProductPersistence;

    public ProductUseCase(IProductPersistence iProductPersistence) {
        this.iProductPersistence = iProductPersistence;
    }

    @Override
    public ResponseModel save(ProductModel product) {
        return new ResponseModel(this.iProductPersistence.save(product));
    }

    @Override
    public List<ProductModel> getAll() {
        return this.iProductPersistence.getAll();
    }

    @Override
    public ProductModel getById(UUID id) {
        return this.iProductPersistence.getById(id);
    }
}
