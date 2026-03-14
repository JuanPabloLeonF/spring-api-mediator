package dev.juanleon.spring_api.product.application.handler;

import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.handler.gets.IProductGetHandler;
import dev.juanleon.spring_api.product.application.handler.pots.IProductSaveHandler;
import dev.juanleon.spring_api.product.application.mapper.IMapperProductApplication;
import dev.juanleon.spring_api.product.application.mapper.IMapperResponseApplication;
import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.domain.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductHandler implements IProductSaveHandler, IProductGetHandler {

    private final IProductService iProductService;
    private final IMapperProductApplication iMapperProductApplication;
    private final IMapperResponseApplication iMapperResponseApplication;

    public ProductHandler(IProductService iProductService, IMapperProductApplication iMapperProductApplication, IMapperResponseApplication iMapperResponseApplication) {
        this.iProductService = iProductService;
        this.iMapperProductApplication = iMapperProductApplication;
        this.iMapperResponseApplication = iMapperResponseApplication;
    }

    @Override
    public ResponseRequest save(RequestProduct requestProduct) {
        ProductModel productModel = this.iMapperProductApplication.toProductModel(requestProduct);
        return this.iMapperResponseApplication.
                toResponseRequest(this.iProductService.save(productModel));
    }

    @Override
    public List<ResponseProduct> getAll() {
        return this.iMapperProductApplication
                .toResponseProductList(this.iProductService.getAll());
    }

    @Override
    public ResponseProduct getById(UUID id) {
        return this.iMapperProductApplication
                .toResponseProduct(this.iProductService.getById(id));
    }
}
