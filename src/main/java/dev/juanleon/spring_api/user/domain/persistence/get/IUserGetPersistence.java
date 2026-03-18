package dev.juanleon.spring_api.user.domain.persistence.get;

import dev.juanleon.spring_api.user.domain.model.UserModel;

import java.util.List;

public interface IUserGetPersistence {
    public List<UserModel> getAll();
    public UserModel getUserName(String username);
}
