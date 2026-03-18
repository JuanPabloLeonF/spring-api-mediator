package dev.juanleon.spring_api.product.application.mapper;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.domain.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperProductApplication {

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "image", ignore = true)
    })
    public ProductModel toProductModel(RequestProduct requestProduct);
    public ResponseProduct toResponseProduct(ProductModel productModel);
    public List<ResponseProduct> toResponseProductList(List<ProductModel> productModelList);
}
