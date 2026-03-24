package dev.juanleon.spring_api.IT;

import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.entity.UserEntity;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.repository.IUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureRestTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private IUserRepository iUserRepository;

    public ResponseUser userResponse = new ResponseUser();

    public UserEntity userEntity = new UserEntity();

    @BeforeEach
    void setUp() {
        this.userEntity.setRol("admin");
        this.userEntity.setUserName("papo");
        this.userEntity.setName("juan pablo");
        this.userEntity.setPassword("admin");

        this.userEntity = this.iUserRepository.save(this.userEntity);

        this.userResponse.setId(this.userEntity.getId());
        this.userResponse.setUserName(this.userEntity.getUserName());
        this.userResponse.setName(this.userEntity.getName());
        this.userResponse.setRol(this.userEntity.getRol());
    }

    @AfterEach
    void tearDown() {
        this.iUserRepository.delete(this.userEntity);
    }

    @Test
    void shouldReturnListResponseUserWhenIsCalledGetAll() {

        this.restTestClient.get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ResponseUser>>() {})
                .value(response -> {
                    assertNotNull(response);
                    assertEquals( 1, response.size());
                    assertEquals(this.userResponse.getId(), response.get(0).getId());
                    assertEquals(this.userResponse.getUserName(), response.get(0).getUserName());
                    assertEquals(this.userResponse.getRol(), response.get(0).getRol());
                });
    }

    @Test
    void shouldReturnResponseUserWhenIsCalledGetByUserNameWithParamUserName() {

        this.restTestClient.get()
                .uri("api/v1/users/{userName}", this.userResponse.getUserName())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseUser.class)
                .value( response -> {
                    assertNotNull(response);
                    assertEquals(this.userResponse.getId(), response.getId());
                    assertEquals(this.userResponse.getUserName(), response.getUserName());
                    assertEquals(this.userResponse.getRol(), response.getRol());
                });
    }
}
