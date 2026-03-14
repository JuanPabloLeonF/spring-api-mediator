package dev.juanleon.spring_api.product.application.mapper;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.domain.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperProductApplication {

    @Mapping(target = "id", ignore = true)
    public ProductModel toProductModel(RequestProduct requestProduct);
    public ResponseProduct toResponseProduct(ProductModel productModel);
    public List<ResponseProduct> toResponseProductList(List<ProductModel> productModelList);
}
