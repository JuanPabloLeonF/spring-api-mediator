package dev.juanleon.spring_api.user.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.handler.get.IUserGetHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUserQueryHandler implements IRequestHandler<GetAllUserQuery, List<ResponseUser>> {

    private final IUserGetHandler iUserGetHandler;

    public GetAllUserQueryHandler(IUserGetHandler iUserGetHandler) {
        this.iUserGetHandler = iUserGetHandler;
    }

    @Override
    public List<ResponseUser> handle(GetAllUserQuery request) {
        return this.iUserGetHandler.getAll();
    }

    @Override
    public Class<GetAllUserQuery> getRequestType() {
        return GetAllUserQuery.class;
    }
}
