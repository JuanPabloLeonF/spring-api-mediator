package dev.juanleon.spring_api.user.domain.useCase.get;

import dev.juanleon.spring_api.user.domain.model.UserModel;
import dev.juanleon.spring_api.user.domain.persistence.get.IUserGetPersistence;
import dev.juanleon.spring_api.user.domain.service.get.IUserGetService;

import java.util.List;

public class UserGetService implements IUserGetService {

    private final IUserGetPersistence iUserGetPersistence;

    public UserGetService(IUserGetPersistence iUserGetPersistence) {
        this.iUserGetPersistence = iUserGetPersistence;
    }

    @Override
    public List<UserModel> getAll() {
        return this.iUserGetPersistence.getAll();
    }

    @Override
    public UserModel getUserName(String username) {
        return this.iUserGetPersistence.getUserName(username);
    }
}
