package dev.juanleon.spring_api.product.application.handler.pots;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.mapper.IMapperProductApplication;
import dev.juanleon.spring_api.common.utils.mappers.IMapperResponseApp;
import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.service.pots.IProductPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductPostHandler implements IProductPostHandler {

    private final IProductPostService iProductPostService;
    private final IMapperProductApplication iMapperProductApplication;
    private final IMapperResponseApp iMapperResponseApp;

    public ProductPostHandler(IProductPostService iProductPostService, IMapperProductApplication iMapperProductApplication, IMapperResponseApp iMapperResponseApp) {
        this.iProductPostService = iProductPostService;
        this.iMapperProductApplication = iMapperProductApplication;
        this.iMapperResponseApp = iMapperResponseApp;
    }

    @Transactional
    @Override
    public ResponseRequest save(RequestProduct requestProduct) {
        ProductModel productModelMapper = this.iMapperProductApplication.toProductModel(requestProduct);
        return this.iMapperResponseApp.
                toResponseRequest(this.iProductPostService.save(productModelMapper, requestProduct.getImage()));
    }
}
