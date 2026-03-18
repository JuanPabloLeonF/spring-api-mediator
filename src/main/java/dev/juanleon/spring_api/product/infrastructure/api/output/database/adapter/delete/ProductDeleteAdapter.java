package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter.delete;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.persistence.delete.IProductDeletePersistence;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper.IMapperProductInfrastructure;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import dev.juanleon.spring_api.product.infrastructure.api.output.exceptions.NotFoundProductException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductDeleteAdapter implements IProductDeletePersistence  {

    private final IProductRepository iProductRepository;
    private final IMapperProductInfrastructure iMapperProductInfrastructure;

    public ProductDeleteAdapter(IProductRepository iProductRepository, IMapperProductInfrastructure iMapperProductInfrastructure) {
        this.iProductRepository = iProductRepository;
        this.iMapperProductInfrastructure = iMapperProductInfrastructure;
    }

    @Override
    public ProductModel deleteById(UUID id) {
        ProductEntity productFound = this.iProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundProductException("Product not found by id: " + id));
        this.iProductRepository.deleteById(id);
        return this.iMapperProductInfrastructure.toProductModel(productFound);
    }
}
