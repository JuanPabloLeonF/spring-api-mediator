package dev.juanleon.spring_api.product.application.commands.update;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;

import java.util.UUID;

public record UpdateProductCommand(UUID id, RequestProduct requestProduct) implements IRequest<ResponseRequest> {
}
