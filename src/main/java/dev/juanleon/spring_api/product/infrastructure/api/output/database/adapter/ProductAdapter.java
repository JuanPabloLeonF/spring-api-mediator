package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.persistence.IProductPersistence;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper.IMapperProductInfrastructure;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import dev.juanleon.spring_api.product.infrastructure.api.output.exceptions.NotFoundProductException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductAdapter implements IProductPersistence {

    private final IProductRepository iProductRepository;
    private final IMapperProductInfrastructure iMapperProductInfrastructure;

    public ProductAdapter(IProductRepository iProductRepository, IMapperProductInfrastructure iMapperProductInfrastructure) {
        this.iProductRepository = iProductRepository;
        this.iMapperProductInfrastructure = iMapperProductInfrastructure;
    }

    @Override
    public String save(ProductModel product) {

        String response;
        ProductEntity productEntity = this.iMapperProductInfrastructure.toProductEntity(product);
        UUID id = this.iProductRepository.save(productEntity).getId();
        if (id == null) {
            response = "Product not saved";
        } else {
            response = "Product save succesfully width id: " + id;
        }

        return response;
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
