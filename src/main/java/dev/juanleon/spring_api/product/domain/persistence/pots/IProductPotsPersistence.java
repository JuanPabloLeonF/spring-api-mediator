package dev.juanleon.spring_api.product.domain.persistence.pots;

import dev.juanleon.spring_api.product.domain.model.ProductModel;

public interface IProductPotsPersistence {
    public String save(ProductModel product, String urlImage);
}
