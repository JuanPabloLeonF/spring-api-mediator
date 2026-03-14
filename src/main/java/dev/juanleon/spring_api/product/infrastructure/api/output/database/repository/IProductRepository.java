package dev.juanleon.spring_api.product.infrastructure.api.output.database.repository;

import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<ProductEntity, UUID> {
}
