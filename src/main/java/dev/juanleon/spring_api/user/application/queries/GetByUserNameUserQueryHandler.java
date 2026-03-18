package dev.juanleon.spring_api.user.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.handler.get.IUserGetHandler;
import org.springframework.stereotype.Component;

@Component
public class GetByUserNameUserQueryHandler implements IRequestHandler<GetByUserNameUserQuery, ResponseUser> {

    private final IUserGetHandler iUserGetHandler;

    public GetByUserNameUserQueryHandler(IUserGetHandler iUserGetHandler) {
        this.iUserGetHandler = iUserGetHandler;
    }

    @Override
    public ResponseUser handle(GetByUserNameUserQuery request) {
        return this.iUserGetHandler.getByUserName(request.userName());
    }

    @Override
    public Class<GetByUserNameUserQuery> getRequestType() {
        return GetByUserNameUserQuery.class;
    }
}
