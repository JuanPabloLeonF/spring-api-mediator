package dev.juanleon.spring_api.product.application.handler.delete;

import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.common.utils.mappers.IMapperResponseApp;
import dev.juanleon.spring_api.product.domain.service.delete.IProductDeleteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductDeleteHandler implements IProductDeleteHandler{

    private final IProductDeleteService iProductDeleteService;
    private final IMapperResponseApp iMapperResponseApp;

    public ProductDeleteHandler(IProductDeleteService iProductDeleteService, IMapperResponseApp iMapperResponseApp) {
        this.iProductDeleteService = iProductDeleteService;
        this.iMapperResponseApp = iMapperResponseApp;
    }

    @Transactional
    @Override
    public ResponseRequest deleteById(UUID id) {
        return this.iMapperResponseApp
                .toResponseRequest(this.iProductDeleteService.deleteById(id));
    }
}
