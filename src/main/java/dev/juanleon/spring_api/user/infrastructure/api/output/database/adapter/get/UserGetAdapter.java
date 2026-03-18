package dev.juanleon.spring_api.user.infrastructure.api.output.database.adapter.get;

import dev.juanleon.spring_api.user.domain.model.UserModel;
import dev.juanleon.spring_api.user.domain.persistence.get.IUserGetPersistence;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.entity.UserEntity;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.mapper.IMapperUserInfrastructure;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.repository.IUserRepository;
import dev.juanleon.spring_api.user.infrastructure.api.output.exceptions.NotFoundUserException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserGetAdapter implements IUserGetPersistence {

    private final IUserRepository iUserRepository;
    private final IMapperUserInfrastructure iMapperUserInfrastructure;

    public UserGetAdapter(IUserRepository iUserRepository, IMapperUserInfrastructure iMapperUserInfrastructure) {
        this.iUserRepository = iUserRepository;
        this.iMapperUserInfrastructure = iMapperUserInfrastructure;
    }

    @Override
    public List<UserModel> getAll() {
        return this.iMapperUserInfrastructure
                .toModelList(this.iUserRepository.findAll());
    }

    @Override
    public UserModel getUserName(String username) {
        UserEntity userEntity = this.iUserRepository.findByUserName(username)
                .orElseThrow(() -> new NotFoundUserException("No se encontro el usuario con el userName: " + username));
        return this.iMapperUserInfrastructure.toModel(userEntity);
    }
}
