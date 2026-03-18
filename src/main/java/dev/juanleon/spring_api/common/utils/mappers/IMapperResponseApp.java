package dev.juanleon.spring_api.common.utils.mappers;

import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.common.utils.models.ResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface IMapperResponseApp {
    public ResponseRequest toResponseRequest(ResponseModel responseModel);
}
