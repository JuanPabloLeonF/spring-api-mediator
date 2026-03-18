package dev.juanleon.spring_api.product.domain.useCases.update;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.common.utils.models.ResponseModel;
import dev.juanleon.spring_api.product.domain.persistence.update.IProductUpdatePersistence;
import dev.juanleon.spring_api.product.domain.service.update.IProductUpdateService;

import java.util.UUID;

public class ProductUpdateUseCase implements IProductUpdateService {

    private final IProductUpdatePersistence iProductUpdatePersistence;

    public ProductUpdateUseCase(IProductUpdatePersistence iProductUpdatePersistence) {
        this.iProductUpdatePersistence = iProductUpdatePersistence;
    }

    @Override
    public ResponseModel updateById(UUID id, ProductModel productModel) {
        return new ResponseModel(this.iProductUpdatePersistence.updateById(id, productModel));
    }
}
