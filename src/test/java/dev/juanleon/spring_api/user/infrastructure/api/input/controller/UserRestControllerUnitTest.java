package dev.juanleon.spring_api.user.infrastructure.api.input.controller;

import dev.juanleon.spring_api.common.mediator.Mediator;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.queries.GetAllUserQuery;
import dev.juanleon.spring_api.user.application.queries.GetByUserNameUserQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRestControllerUnitTest {

    private RestTestClient restTestClient;

    public Mediator mediator;

    @BeforeEach
    void setUp() {
        this.mediator = mock(Mediator.class);
        UserRestController controller = new UserRestController(this.mediator);
        this.restTestClient = RestTestClient.bindToController(controller).build();
    }

    @Test
    void getAll() {
        List<ResponseUser> mockResponse = List.of(
                new ResponseUser(UUID.randomUUID(), "papo", "juan", "user", "123")
        );

        when(this.mediator.dispatch(any(GetAllUserQuery.class))).thenReturn(mockResponse);

        this.restTestClient
                .get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<ResponseUser>>() {})
                .value((response) -> {
                    assertNotNull(response);
                    assertEquals(1, response.size());
                    assertEquals("papo", response.get(0).getUserName());
                })
                .returnResult();
    }

    @Test
    void getByUserName() {

        String userName = "papo";

        ResponseUser responseUser = new ResponseUser(UUID.randomUUID(), userName, "juan", "user", "123");

        when(this.mediator.dispatch(any(GetByUserNameUserQuery.class))).thenReturn(responseUser);

        this.restTestClient
                .get()
                .uri("/api/v1/users/{userName}", userName)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<ResponseUser>() {})
                .value((response) -> {
                    assertNotNull(response);
                    assertEquals(userName, response.getUserName());
                })
                .returnResult();
    }
}