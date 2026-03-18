package dev.juanleon.spring_api.product.domain.useCases.post;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.common.utils.models.ResponseModel;
import dev.juanleon.spring_api.product.domain.persistence.delete.IFileStoragePersistence;
import dev.juanleon.spring_api.product.domain.persistence.pots.IProductPotsPersistence;
import dev.juanleon.spring_api.product.domain.service.pots.IProductPostService;
import org.springframework.web.multipart.MultipartFile;

public class ProductPostUseCase implements IProductPostService {

    private final IProductPotsPersistence iProductPotsPersistence;
    private final IFileStoragePersistence iFileStoragePersistence;

    public ProductPostUseCase(IProductPotsPersistence iProductPotsPersistence, IFileStoragePersistence iFileStoragePersistence) {
        this.iProductPotsPersistence = iProductPotsPersistence;
        this.iFileStoragePersistence = iFileStoragePersistence;
    }

    @Override
    public ResponseModel save(ProductModel product, MultipartFile file) {
        String urlImage = this.iFileStoragePersistence.save(file);
        return new ResponseModel(this.iProductPotsPersistence.save(product, urlImage));
    }
}
