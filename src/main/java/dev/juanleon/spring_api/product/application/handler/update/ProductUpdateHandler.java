package dev.juanleon.spring_api.product.application.handler.update;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.mapper.IMapperProductApplication;
import dev.juanleon.spring_api.common.utils.mappers.IMapperResponseApp;
import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.service.update.IProductUpdateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductUpdateHandler implements IProductUpdateHandler{

    private final IProductUpdateService iProductUpdateService;
    private final IMapperResponseApp iMapperResponseApp;
    private final IMapperProductApplication iMapperProductApplication;

    public ProductUpdateHandler(IProductUpdateService iProductUpdateService, IMapperResponseApp iMapperResponseApp, IMapperProductApplication iMapperProductApplication) {
        this.iProductUpdateService = iProductUpdateService;
        this.iMapperResponseApp = iMapperResponseApp;
        this.iMapperProductApplication = iMapperProductApplication;
    }

    @Transactional
    @Override
    public ResponseRequest updateById(UUID id, RequestProduct requestProduct) {
        ProductModel productModel = this.iMapperProductApplication.toProductModel(requestProduct);
        return this.iMapperResponseApp
                .toResponseRequest(this.iProductUpdateService.updateById(id, productModel));
    }
}
