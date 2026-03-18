package dev.juanleon.spring_api.product.domain.service.delete;

import dev.juanleon.spring_api.common.utils.models.ResponseModel;

import java.util.UUID;

public interface IProductDeleteService {
    public ResponseModel deleteById(UUID id);
}
