package dev.juanleon.spring_api.user.domain.service.get;

import dev.juanleon.spring_api.user.domain.model.UserModel;

import java.util.List;

public interface IUserGetService {
    public List<UserModel> getAll();
    public UserModel getUserName(String username);
}
