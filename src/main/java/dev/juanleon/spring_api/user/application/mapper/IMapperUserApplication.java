package dev.juanleon.spring_api.user.application.mapper;

import dev.juanleon.spring_api.user.application.dto.RequestUser;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import java.util.List;

@Mapper(componentModel = ComponentModel.SPRING)
public interface IMapperUserApplication {
    @Mapping(target = "id", ignore = true)
    public UserModel toModel(RequestUser requestUser);
    public ResponseUser toResponse(UserModel userModel);
    public List<ResponseUser> toResponseList(List<UserModel> userModelList);
}
