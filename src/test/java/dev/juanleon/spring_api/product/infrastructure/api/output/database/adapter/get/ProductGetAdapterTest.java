package dev.juanleon.spring_api.product.infrastructure.api.output.database.adapter.get;

import dev.juanleon.spring_api.product.domain.model.ProductModel;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.mapper.IMapperProductInfrastructure;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import dev.juanleon.spring_api.product.infrastructure.api.output.exceptions.NotFoundProductException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductGetAdapterTest {

    @Mock
    private IProductRepository iProductRepository;
    @Mock
    private IMapperProductInfrastructure iMapperProductInfrastructure;
    @InjectMocks
    private ProductGetAdapter productGetAdapter;

    @Test
    public void shouldReturnListOfProductsWhenGetAllIsCalled() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        List<ProductEntity> dataEntity = List.of(
                new ProductEntity(id1, "manzana 1", 1500.0, "img/ruta/1"),
                new ProductEntity(id2, "manzana 2", 700.0, "img/ruta/2"),
                new ProductEntity(id3, "manzana 3", 800.0, "img/ruta/3")
        );

        List<ProductModel> dataModel = List.of(
                new ProductModel(id1, "manzana 1", 1500.0, "img/ruta/1"),
                new ProductModel(id2, "manzana 2", 700.0, "img/ruta/2"),
                new ProductModel(id3, "manzana 3", 800.0, "img/ruta/3")
        );

        when(this.iProductRepository.findAll())
                .thenReturn(dataEntity);

        when(this.iMapperProductInfrastructure.toProductModelList(dataEntity))
                .thenReturn(dataModel);


        List<ProductModel> result = this.productGetAdapter.getAll();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("manzana 1", result.get(0).name());
        assertEquals(800.0, result.get(2).price());
        assertEquals("img/ruta/2", result.get(1).image());

        verify(this.iProductRepository).findAll();
        verify(this.iMapperProductInfrastructure).toProductModelList(dataEntity);
    }

    @Test
    public void shouldReturnProductWhenFoundById() {

        UUID id = UUID.randomUUID();

        ProductEntity entity = new ProductEntity(id, "manzana 1", 500.0, "img/ruta/1");
        ProductModel model = new ProductModel(id, "manzana 1", 500.0, "img/ruta/1");

        when(iProductRepository.findById(id))
                .thenReturn(Optional.of(entity));

        when(iMapperProductInfrastructure.toProductModel(entity))
                .thenReturn(model);

        ProductModel result = productGetAdapter.getById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("manzana 1", result.name());

        verify(iProductRepository).findById(id);
        verify(iMapperProductInfrastructure).toProductModel(entity);
    }

    @Test
    public void shouldReturnNotFoundProductExceptionWhenNotFoundProductById() {
        UUID id = UUID.randomUUID();

        when(iProductRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundProductException.class, () -> {
            productGetAdapter.getById(id);
        });

        verify(iProductRepository).findById(id);
        verify(iMapperProductInfrastructure, never()).toProductModel(any());
    }
}