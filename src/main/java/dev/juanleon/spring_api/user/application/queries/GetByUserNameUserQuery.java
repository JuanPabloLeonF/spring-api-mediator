package dev.juanleon.spring_api.user.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;

public record GetByUserNameUserQuery(String userName) implements IRequest<ResponseUser> {
}
