package dev.juanleon.spring_api.user.domain.useCase.get;

import dev.juanleon.spring_api.user.domain.model.UserModel;
import dev.juanleon.spring_api.user.domain.persistence.get.IUserGetPersistence;
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
class UserGetServiceTest {

    @Mock
    private IUserGetPersistence iUserGetPersistence;

    @InjectMocks
    private UserGetService userGetService;

    private UUID id1;
    private UUID id2;

    @BeforeEach
    void setUp() {
        this.id1 = UUID.randomUUID();
        this.id2 = UUID.randomUUID();
    }

    @Test
    void shouldReturnListOfUserModelWhenIsCalledGetAll() {
        List<UserModel> userModelList = List.of(
                new UserModel(this.id1, "papo", "juan leon", "user", "papo123"),
                new UserModel(this.id2, "felipe", "andres leon", "admin", "felipe123")
        );

        when(this.iUserGetPersistence.getAll()).thenReturn(userModelList);

        List<UserModel> response = this.userGetService.getAll();

        assertEquals(userModelList, response);
        assertEquals(2, response.size());

        verify(this.iUserGetPersistence).getAll();
    }

    @Test
    void shouldReturnUserModelWhenIsCalledGetUserNameWithParamUserName() {

        String userName = "papo";

        UserModel userModel = new UserModel(this.id1, "papo", "juan leon", "user", "papo123");

        when(this.iUserGetPersistence.getUserName(userName)).thenReturn(userModel);

        UserModel response = this.userGetService.getUserName(userName);

        assertEquals(userModel, response);
        assertEquals(this.id1, response.id());

        verify(this.iUserGetPersistence).getUserName(userName);
    }
}