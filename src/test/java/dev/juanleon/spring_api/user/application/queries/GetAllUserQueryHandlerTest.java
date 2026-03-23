package dev.juanleon.spring_api.user.application.queries;

import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.handler.get.IUserGetHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllUserQueryHandlerTest {

    @Mock
    private IUserGetHandler iUserGetHandler;

    @InjectMocks
    private GetAllUserQueryHandler getAllUserQueryHandler;

    private UUID id1;

    @BeforeEach
    void setUp() {
        this.id1 = UUID.randomUUID();
    }

    @Test
    void shouldReturnListResponseUserWhenHandleIsCalled() {
        GetAllUserQuery query = new GetAllUserQuery();

        List<ResponseUser> expectedList = List.of(
                new ResponseUser(this.id1, "papo", "juan leon", "user", "papo123")
        );

        when(iUserGetHandler.getAll()).thenReturn(expectedList);
        List<ResponseUser> response = getAllUserQueryHandler.handle(query);

        assertNotNull(response);
        assertEquals(expectedList, response);
        assertEquals(1, response.size());

        verify(iUserGetHandler).getAll();
    }

    @Test
    void shouldReturnCorrectRequestType() {
        Class<GetAllUserQuery> result = getAllUserQueryHandler.getRequestType();
        assertEquals(GetAllUserQuery.class, result);
    }
}