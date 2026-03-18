package dev.juanleon.spring_api.product.application.handler.gets;

import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.application.mapper.IMapperProductApplication;
import dev.juanleon.spring_api.product.domain.service.gets.IProductGetsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductGetHandler implements IProductGetHandler {

    private final IProductGetsService iProductGetsService;
    private final IMapperProductApplication iMapperProductApplication;

    public ProductGetHandler(IProductGetsService iProductGetsService, IMapperProductApplication iMapperProductApplication) {
        this.iProductGetsService = iProductGetsService;
        this.iMapperProductApplication = iMapperProductApplication;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseProduct> getAll() {
        return this.iMapperProductApplication
                .toResponseProductList(this.iProductGetsService.getAll());
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseProduct getById(UUID id) {
        return this.iMapperProductApplication
                .toResponseProduct(this.iProductGetsService.getById(id));
    }
}
