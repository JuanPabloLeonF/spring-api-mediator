package dev.juanleon.spring_api.product.application.mapper;

import dev.juanleon.spring_api.product.application.dto.ResponseRequest;
import dev.juanleon.spring_api.product.domain.model.ResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapperResponseApplication {
    public ResponseRequest toResponseRequest(ResponseModel responseModel);
}
