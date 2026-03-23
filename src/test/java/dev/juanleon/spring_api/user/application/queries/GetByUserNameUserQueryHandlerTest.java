package dev.juanleon.spring_api.user.application.queries;

import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.handler.get.IUserGetHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetByUserNameUserQueryHandlerTest {

    @Mock
    private IUserGetHandler iUserGetHandler;

    @InjectMocks
    private GetByUserNameUserQueryHandler getByUserNameUserQueryHandler;

    private UUID id1;

    @BeforeEach
    void setUp() {
        this.id1 = UUID.randomUUID();
    }

    @Test
    void shouldReturnResponseUserWhenHandleIsCalled() {

        String userName = "papo";
        GetByUserNameUserQuery query = new GetByUserNameUserQuery(userName);

        ResponseUser responseUser = new ResponseUser(this.id1, "papo", "juan leon", "user", "papo123");

        when(this.iUserGetHandler.getByUserName(userName)).thenReturn(responseUser);
        ResponseUser response = this.getByUserNameUserQueryHandler.handle(query);

        assertNotNull(response);
        assertEquals(responseUser, response);

        verify(this.iUserGetHandler).getByUserName(userName);
    }

    @Test
    void shouldReturnCorrectRequestType() {
        Class<GetByUserNameUserQuery> result = this.getByUserNameUserQueryHandler.getRequestType();
        assertEquals(GetByUserNameUserQuery.class, result);
    }
}