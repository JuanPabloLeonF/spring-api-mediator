package dev.juanleon.spring_api.IT;

import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.product.infrastructure.api.output.database.repository.IProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.startsWith;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class ProductIT {

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private IProductRepository iProductRepository;

    private UUID savedId;


    @BeforeEach
    public void setUp() {
        this.savedId = UUID.fromString("5f281964-93aa-4cd2-8ba6-e38c9b5d589d");
    }

    @AfterEach
    public void tearDown() {
        //this.iProductRepository.deleteById(this.savedId)
        IO.println("products: " + this.iProductRepository.findAll());
    }

    @Test
    public void shouldReturnProductWhenGetByIdIsCalled() {
        restTestClient.get()
                .uri("/api/v1/products/{id}", this.savedId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseProduct.class)
                .value(response -> {
                    assertNotNull(response);
                    assertEquals(this.savedId, response.getId());
                    assertEquals("manzana", response.getName());
                });
    }

    @Test
    public void shouldReturnCreatedWhenSaveProductWithImage() {
        byte[] imageContent = "imagen-binaria-de-prueba".getBytes();
        ByteArrayResource imageResource = new ByteArrayResource(imageContent) {
            @Override
            public String getFilename() {
                return "test-image.jpg";
            }
        };

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("name", "Producto T123");
        formData.add("price", 5500.0);
        formData.add("image", imageResource);

        restTestClient.post()
                .uri("/api/v1/products")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(formData)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.message").exists()
                .jsonPath("$.message").isEqualTo(startsWith("Product save succesfully width id:"))
                .jsonPath("$.dateTime").exists();
    }
}
