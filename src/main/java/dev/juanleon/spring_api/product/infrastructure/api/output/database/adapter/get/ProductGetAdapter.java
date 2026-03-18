package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter.get;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.persistence.gets.IProductGetsPersistence;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper.IMapperProductInfrastructure;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import dev.juanleon.spring_api.product.infrastructure.api.output.exceptions.NotFoundProductException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductGetAdapter implements IProductGetsPersistence {

    private final IProductRepository iProductRepository;
    private final IMapperProductInfrastructure iMapperProductInfrastructure;

    public ProductGetAdapter(IProductRepository iProductRepository, IMapperProductInfrastructure iMapperProductInfrastructure) {
        this.iProductRepository = iProductRepository;
        this.iMapperProductInfrastructure = iMapperProductInfrastructure;
    }

    @Override
    public List<ProductModel> getAll() {
        return this.iMapperProductInfrastructure
                .toProductModelList(this.iProductRepository.findAll());
    }

    @Override
    public ProductModel getById(UUID id) {
        ProductEntity productEntity = this.iProductRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundProductException("Product not found width id: " + id));
        return this.iMapperProductInfrastructure.toProductModel(productEntity);
    }
}
