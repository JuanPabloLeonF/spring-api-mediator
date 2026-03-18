package dev.juanleon.spring_api.user.infrastructure.api.output.database.mapper;

import dev.juanleon.spring_api.user.domain.model.UserModel;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
        componentModel = ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface IMapperUserInfrastructure {
    public UserModel toModel(UserEntity userEntity);
    public UserEntity toEntity(UserModel userModel);
    public List<UserModel> toModelList(List<UserEntity> userEntityList);
}
