package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter.update;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.persistence.update.IProductUpdatePersistence;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper.IMapperProductInfrastructure;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import dev.juanleon.spring_api.product.infrastructure.api.output.exceptions.NotFoundProductException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductUpdateAdapter implements IProductUpdatePersistence {

    private final IProductRepository iProductRepository;
    private final IMapperProductInfrastructure iMapperProductInfrastructure;

    public ProductUpdateAdapter(IProductRepository iProductRepository, IMapperProductInfrastructure iMapperProductInfrastructure) {
        this.iProductRepository = iProductRepository;
        this.iMapperProductInfrastructure = iMapperProductInfrastructure;
    }

    @Override
    public String updateById(UUID id, ProductModel productModel) {
        UUID idFound = this.iProductRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundProductException("Product not found width id: " + id))
                .getId();

        ProductEntity productEntity = this.iMapperProductInfrastructure.toProductEntity(productModel);
        productEntity.setId(idFound);
        this.iProductRepository.save(productEntity);

        return "Product updated succesfully width id: " + idFound;
    }
}
