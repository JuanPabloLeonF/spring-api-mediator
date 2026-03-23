package dev.juanleon.spring_api.user.application.handler.get;

import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.mapper.IMapperUserApplication;
import dev.juanleon.spring_api.user.domain.model.UserModel;
import dev.juanleon.spring_api.user.domain.service.get.IUserGetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGetHandlerTest {

    @Mock
    private IUserGetService iUserGetService;

    @Mock
    private IMapperUserApplication iMapperUserApplication;

    @InjectMocks
    private UserGetHandler userGetHandler;

    private UUID id1;
    private UUID id2;

    @BeforeEach
    void setUp() {
        this.id1 = UUID.randomUUID();
        this.id2 = UUID.randomUUID();
    }

    @Test
    void shouldReturnListResponseUserWhenIsCalledGetAll() {

        List<UserModel> userModelList = List.of(
                new UserModel(this.id1, "papo", "juan leon", "user", "papo123"),
                new UserModel(this.id2, "felipe", "andres leon", "admin", "felipe123")
        );

        List<ResponseUser> responseUserList = List.of(
                new ResponseUser(this.id1, "papo", "juan leon", "user", "papo123"),
                new ResponseUser(this.id2, "felipe", "andres leon", "admin", "felipe123")
        );

        when(this.iUserGetService.getAll()).thenReturn(userModelList);
        when(this.iMapperUserApplication.toResponseList(userModelList)).thenReturn(responseUserList);

        List<ResponseUser> response = this.userGetHandler.getAll();

        assertEquals(responseUserList, response);
        assertEquals(2, response.size());

        verify(this.iUserGetService).getAll();
        verify(this.iMapperUserApplication).toResponseList(userModelList);
    }

    @Test
    void shouldReturnResponseUserWhenIsCalledGetUserNameWithParamUserName() {

        String userName = "papo";

        UserModel userModel = new UserModel(this.id1, userName, "juan leon", "user", "papo123");
        ResponseUser responseUser = new ResponseUser(this.id1, userName, "juan leon", "user", "papo123");

        when(this.iUserGetService.getUserName(userName)).thenReturn(userModel);
        when(this.iMapperUserApplication.toResponse(userModel)).thenReturn(responseUser);

        ResponseUser response = this.userGetHandler.getByUserName(userName);

        assertEquals(responseUser, response);
        assertEquals(this.id1, response.getId());

        verify(this.iUserGetService).getUserName(userName);
        verify(this.iMapperUserApplication).toResponse(userModel);
    }
}