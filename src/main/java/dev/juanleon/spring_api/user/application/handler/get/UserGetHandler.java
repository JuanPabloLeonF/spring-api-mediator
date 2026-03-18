package dev.juanleon.spring_api.user.application.handler.get;

import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.mapper.IMapperUserApplication;
import dev.juanleon.spring_api.user.domain.service.get.IUserGetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGetHandler implements IUserGetHandler {

    private final IUserGetService iUserGetService;
    private final IMapperUserApplication iMapperUserApplication;

    public UserGetHandler(IUserGetService iUserGetService, IMapperUserApplication iMapperUserApplication) {
        this.iUserGetService = iUserGetService;
        this.iMapperUserApplication = iMapperUserApplication;
    }

    @Override
    public List<ResponseUser> getAll() {
        return this.iMapperUserApplication
                .toResponseList(this.iUserGetService.getAll());
    }

    @Override
    public ResponseUser getByUserName(String username) {
        return this.iMapperUserApplication
                .toResponse(this.iUserGetService.getUserName(username));
    }
}
