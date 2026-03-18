package dev.juanleon.spring_api.product.domain.useCases.delete;

import dev.juanleon.spring_api.common.utils.models.ResponseModel;
import dev.juanleon.spring_api.product.domain.persistence.delete.IFileStoragePersistence;
import dev.juanleon.spring_api.product.domain.persistence.delete.IProductDeletePersistence;
import dev.juanleon.spring_api.product.domain.service.delete.IProductDeleteService;

import java.util.UUID;

public class ProductDeleteUseCase implements IProductDeleteService {

    private final IProductDeletePersistence iProductDeletePersistence;
    private final IFileStoragePersistence iFileStoragePersistence;

    public ProductDeleteUseCase(IProductDeletePersistence iProductDeletePersistence, IFileStoragePersistence iFileStoragePersistence) {
        this.iProductDeletePersistence = iProductDeletePersistence;
        this.iFileStoragePersistence = iFileStoragePersistence;
    }

    @Override
    public ResponseModel deleteById(UUID id) {
        String urlImage = this.iProductDeletePersistence.deleteById(id).image();
        this.iFileStoragePersistence.deleteFile(urlImage);
        String response = "Product delete succesfully with id: " + id;
        return new ResponseModel(response);
    }
}
