package dev.juanleon.spring_api.user.application.handler.get;

import dev.juanleon.spring_api.user.application.dto.ResponseUser;

import java.util.List;

public interface IUserGetHandler {
    public List<ResponseUser> getAll();
    public ResponseUser getByUserName(String username);
}
