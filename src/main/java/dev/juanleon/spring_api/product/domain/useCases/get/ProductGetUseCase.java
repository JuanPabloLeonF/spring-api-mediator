package dev.juanleon.spring_api.product.domain.useCases.get;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.persistence.gets.IProductGetsPersistence;
import dev.juanleon.spring_api.product.domain.service.gets.IProductGetsService;

import java.util.List;
import java.util.UUID;

public class ProductGetUseCase  implements IProductGetsService {

    private final IProductGetsPersistence iProductGetsPersistence;

    public ProductGetUseCase(IProductGetsPersistence iProductGetsPersistence) {
        this.iProductGetsPersistence = iProductGetsPersistence;
    }

    @Override
    public List<ProductModel> getAll() {
        return this.iProductGetsPersistence.getAll();
    }

    @Override
    public ProductModel getById(UUID id) {
        return this.iProductGetsPersistence.getById(id);
    }
}
