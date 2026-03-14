package dev.juanleon.spring_api.product.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.application.handler.gets.IProductGetHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsQueryHandler implements IRequestHandler<GetAllProductsQuery, List<ResponseProduct>> {

    private final IProductGetHandler iProductGetHandler;

    public GetAllProductsQueryHandler(IProductGetHandler iProductGetHandler) {
        this.iProductGetHandler = iProductGetHandler;
    }

    @Override
    public List<ResponseProduct> handle(GetAllProductsQuery request) {
        return iProductGetHandler.getAll();
    }

    @Override
    public Class<GetAllProductsQuery> getRequestType() {
        return GetAllProductsQuery.class;
    }
}
