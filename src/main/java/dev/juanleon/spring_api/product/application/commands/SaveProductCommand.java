package dev.juanleon.spring_api.product.application.commands;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseRequest;

public record SaveProductCommand(RequestProduct requestProduct) implements IRequest<ResponseRequest> {
}
