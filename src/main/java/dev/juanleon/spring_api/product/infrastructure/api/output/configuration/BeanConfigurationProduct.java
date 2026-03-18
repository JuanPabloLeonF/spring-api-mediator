package dev.juanleon.spring_api.product.infrastructure.api.output.configuration;

import dev.juanleon.spring_api.product.domain.persistence.delete.IFileStoragePersistence;
import dev.juanleon.spring_api.product.domain.persistence.delete.IProductDeletePersistence;
import dev.juanleon.spring_api.product.domain.persistence.gets.IProductGetsPersistence;
import dev.juanleon.spring_api.product.domain.persistence.pots.IProductPotsPersistence;
import dev.juanleon.spring_api.product.domain.persistence.update.IProductUpdatePersistence;
import dev.juanleon.spring_api.product.domain.service.delete.IProductDeleteService;
import dev.juanleon.spring_api.product.domain.service.gets.IProductGetsService;
import dev.juanleon.spring_api.product.domain.service.pots.IProductPostService;
import dev.juanleon.spring_api.product.domain.service.update.IProductUpdateService;
import dev.juanleon.spring_api.product.domain.useCases.delete.ProductDeleteUseCase;
import dev.juanleon.spring_api.product.domain.useCases.get.ProductGetUseCase;
import dev.juanleon.spring_api.product.domain.useCases.post.ProductPostUseCase;
import dev.juanleon.spring_api.product.domain.useCases.update.ProductUpdateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurationProduct {

    @Bean
    public IProductUpdateService iProductUpdateService(IProductUpdatePersistence iProductUpdatePersistence) {
        return new ProductUpdateUseCase(iProductUpdatePersistence);
    }

    @Bean
    public IProductDeleteService iProductDeleteService(
            IProductDeletePersistence iProductDeletePersistence,
            IFileStoragePersistence iFileStoragePersistence
    ) {
        return new ProductDeleteUseCase(
                iProductDeletePersistence,
                iFileStoragePersistence
        );
    }

    @Bean
    public IProductGetsService iProductGetsService(IProductGetsPersistence iProductGetsPersistence) {
        return new ProductGetUseCase(iProductGetsPersistence);
    }

    @Bean
    public IProductPostService iProductPostService(
            IProductPotsPersistence iProductPotsPersistence,
            IFileStoragePersistence iFileStoragePersistence
            ) {
        return new ProductPostUseCase(iProductPotsPersistence, iFileStoragePersistence);
    }
}
