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
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ProductIT {

//    @Autowired
//    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductRepository iProductRepository;

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

//    @Test
//    public void shouldReturnProductWhenGetByIdIsCalled() {
//
//        ResponseEntity<ResponseProduct> response = testRestTemplate.getForEntity(
//                "/api/v1/products/" + ID_RANDOM,
//                ResponseProduct.class
//        );
//
//        ResponseProduct product = response.getBody();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(product);
//        assertEquals(ID_RANDOM, product.getId());
//        assertEquals("manzana 1", product.getName());
//    }

    @Test
    public void shouldReturnStatusOKWhenSaveProduct() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "image.jpeg",
                "image/jpeg",
                "image".getBytes()
        );

        mockMvc.perform(multipart(HttpMethod.POST, "/api/v1/products")
                .file(mockMultipartFile)
                .param("name", "producto 5")
                .param("price", "8000")
                .contentType(MediaType.MULTIPART_FORM_DATA)

        ).andExpect(status().isCreated());
    }
}
