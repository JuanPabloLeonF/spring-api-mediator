package dev.juanleon.spring_api.product.infrastructure.api.input.controller;

import dev.juanleon.spring_api.common.mediator.Mediator;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.application.queries.GetAllProductsQuery;
import dev.juanleon.spring_api.product.application.queries.GetByIdProductQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductRestControllerTest {

    @Mock
    private Mediator mediator;

    @InjectMocks
    private ProductRestController productRestController;

    @Test
    public void getAll() {

        List<ResponseProduct> data = List.of(
                new ResponseProduct(UUID.randomUUID(), "manzana 1", 500.0, "img/ruta/1"),
                new ResponseProduct(UUID.randomUUID(), "manzana 2", 1500.0, "img/ruta/2"),
                new ResponseProduct(UUID.randomUUID(), "manzana 3", 700.0, "img/ruta/3")
        );

        when(mediator.dispatch(any(GetAllProductsQuery.class)))
                .thenReturn(data);

        ResponseEntity<List<ResponseProduct>> response = this.productRestController.getAll();

        List<ResponseProduct> products = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(products);
        assertEquals(3, products.size());
    }

    @Test
    public void getById() {

        UUID id = UUID.randomUUID();

        ResponseProduct data = new ResponseProduct(
                id,
                "manzana 1",
                500.0,
                "img/ruta/1"
        );

        when(mediator.dispatch(any(GetByIdProductQuery.class)))
                .thenReturn(data);

        ResponseEntity<ResponseProduct> response = this.productRestController.getById(id);

        ResponseProduct product = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(product);
        assertEquals("manzana 1", product.getName());
    }
}