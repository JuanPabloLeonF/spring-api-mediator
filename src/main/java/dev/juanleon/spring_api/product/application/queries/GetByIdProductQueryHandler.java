package dev.juanleon.spring_api.product.application.queries;

import dev.juanleon.spring_api.common.mediator.IRequestHandler;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.application.handler.gets.IProductGetHandler;
import org.springframework.stereotype.Component;

@Component
public class GetByIdProductQueryHandler implements IRequestHandler<GetByIdProductQuery, ResponseProduct> {

    private final IProductGetHandler iProductGetHandler;

    public GetByIdProductQueryHandler(IProductGetHandler iProductGetHandler) {
        this.iProductGetHandler = iProductGetHandler;
    }

    @Override
    public ResponseProduct handle(GetByIdProductQuery request) {
        return iProductGetHandler.getById(request.id());
    }

    @Override
    public Class<GetByIdProductQuery> getRequestType() {
        return GetByIdProductQuery.class;
    }
}
