package dev.juanleon.spring_api.product.infrastructure.api.output.configuration;

import dev.juanleon.spring_api.product.domain.persistence.IProductPersistence;
import dev.juanleon.spring_api.product.domain.service.IProductService;
import dev.juanleon.spring_api.product.domain.useCases.ProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IProductService iProductService(IProductPersistence iProductPersistence) {
        return new ProductUseCase(iProductPersistence);
    }
}
