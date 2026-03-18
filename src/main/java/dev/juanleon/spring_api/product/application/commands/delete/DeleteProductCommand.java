package dev.juanleon.spring_api.product.application.commands.delete;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;

import java.util.UUID;

public record DeleteProductCommand(UUID id) implements IRequest<ResponseRequest> {
}
