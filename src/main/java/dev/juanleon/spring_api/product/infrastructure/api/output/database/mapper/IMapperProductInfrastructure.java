package dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperProductInfrastructure {
    public ProductEntity toProductEntity(ProductModel productModel);
    public ProductModel toProductModel(ProductEntity productEntity);
    public List<ProductModel> toProductModelList(List<ProductEntity> productEntityList);
}
