package dev.juanleon.spring_api.product.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequest;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;

import java.util.List;

public record GetAllProductsQuery() implements IRequest<List<ResponseProduct>> {
}
