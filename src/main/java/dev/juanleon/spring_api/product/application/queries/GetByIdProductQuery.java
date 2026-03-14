package dev.juanleon.spring_api.product.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;

import java.util.UUID;

public record GetByIdProductQuery(UUID id) implements IRequest<ResponseProduct> {}
