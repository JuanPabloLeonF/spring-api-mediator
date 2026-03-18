package dev.juanleon.spring_api.user.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;

import java.util.List;

public record GetAllUserQuery() implements IRequest<List<ResponseUser>> {
}
