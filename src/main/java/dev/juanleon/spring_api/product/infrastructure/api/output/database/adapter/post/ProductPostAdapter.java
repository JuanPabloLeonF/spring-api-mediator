package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter.post;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.persistence.pots.IProductPotsPersistence;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper.IMapperProductInfrastructure;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductPostAdapter implements IProductPotsPersistence {

    private final IProductRepository iProductRepository;
    private final IMapperProductInfrastructure iMapperProductInfrastructure;

    public ProductPostAdapter(IProductRepository iProductRepository, IMapperProductInfrastructure iMapperProductInfrastructure) {
        this.iProductRepository = iProductRepository;
        this.iMapperProductInfrastructure = iMapperProductInfrastructure;
    }

    @Override
    public String save(ProductModel product, String urlImage) {

        String response;
        ProductEntity productEntity = this.iMapperProductInfrastructure.toProductEntity(product);
        productEntity.setImage(urlImage);

        UUID id = this.iProductRepository.save(productEntity).getId();
        if (id == null) {
            response = "Product not saved";
        } else {
            response = "Product save succesfully width id: " + id;
        }

        return response;
    }
}
