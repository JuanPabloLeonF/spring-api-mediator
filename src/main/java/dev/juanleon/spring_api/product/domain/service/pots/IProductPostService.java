package dev.juanleon.spring_api.product.domain.service.pots;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.common.utils.models.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface IProductPostService {
    public ResponseModel save(ProductModel product, MultipartFile file);
}
