package dev.juanleon.spring_api.IT;

import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.entity.ProductEntity;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private IProductRepository iProductRepository;
    @LocalServerPort
    private int port;

    private static final UUID ID_RANDOM = UUID.randomUUID();


    @BeforeEach
    public void setUp() {
        ProductEntity entity = new ProductEntity(ID_RANDOM,"manzana 1", 500.0, "img/ruta/1");
        this.iProductRepository.save(entity);
    }

    @AfterEach
    public void tearDown() {
        this.iProductRepository.deleteById(ID_RANDOM);
    }

    @Test
    public void shouldReturnProductWhenGetByIdIsCalled() {

        String url = "http://localhost:" + port + "/api/v1/products/" + ID_RANDOM;

        ResponseEntity<ResponseProduct> responseEntity = this.testRestTemplate
                .getForEntity(url, ResponseProduct.class);

        ResponseProduct product = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(product);
        assertEquals(ID_RANDOM, product.getId());
        assertEquals("manzana 1", product.getName());
    }
}
