package dev.juanleon.spring_api.user.infrastructure.api.input.controller;


import dev.juanleon.spring_api.common.mediator.Mediator;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.queries.GetAllUserQuery;
import dev.juanleon.spring_api.user.application.queries.GetByUserNameUserQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(UserRestController.class)
class UserRestControllerMockMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Mediator mediator;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindTo(this.mockMvc).build();
    }

    @Test
    void shouldReturnStatusOKAndVerifyMappingWhenIsCalledGetAll() {
        when(mediator.dispatch(any(GetAllUserQuery.class))).thenReturn(List.of());

        restTestClient.get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk();

        verify(mediator).dispatch(any(GetAllUserQuery.class));
    }

    @Test
    void shouldPassPathVariableWhenIsCalledGetByUserName() {
        String userName = "juanleon";
        ResponseUser mockResponse = new ResponseUser();
        when(mediator.dispatch(any())).thenReturn(mockResponse);

        restTestClient.get()
                .uri("/api/v1/users/{userName}", userName)
                .exchange()
                .expectStatus().isOk();

        ArgumentCaptor<GetByUserNameUserQuery> queryCaptor = ArgumentCaptor
                .forClass(GetByUserNameUserQuery.class);

        verify(mediator).dispatch(queryCaptor.capture());
        assertEquals(userName, queryCaptor.getValue().userName());
    }

    @Test
    void shouldReturnBadRequestWhenDataIntegrityViolationOccurs() {
        when(mediator.dispatch(any()))
                .thenThrow(new DataIntegrityViolationException("Duplicate key"));

        restTestClient.get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ProblemDetail.class)
                .value(response -> {
                    assertNotNull(response);
                    assertEquals("Bad Request", response.getTitle());
                    assertEquals("Duplicate key", response.getDetail());
                });
    }

    @Test
    void shouldReturnBadRequestWhenUserValidationFails() {
        String invalidJson = "{\"userName\": \"\"}";

        restTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(invalidJson)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ProblemDetail.class)
                .value(response -> {
                    assertNotNull(response);
                    assertEquals("Bad Request", response.getTitle());
                    assertNotNull(response.getDetail());
                });
    }

}