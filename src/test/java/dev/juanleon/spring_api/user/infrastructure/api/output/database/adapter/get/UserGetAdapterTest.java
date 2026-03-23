package dev.juanleon.spring_api.user.infrastructure.api.output.database.adapter.get;

import dev.juanleon.spring_api.user.domain.model.UserModel;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.entity.UserEntity;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.mapper.IMapperUserInfrastructure;
import dev.juanleon.spring_api.user.infrastructure.api.output.database.repository.IUserRepository;
import dev.juanleon.spring_api.user.infrastructure.api.output.exceptions.NotFoundUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGetAdapterTest {

    @Mock
    private IUserRepository iUserRepository;

    @Mock
    private IMapperUserInfrastructure iMapperUserInfrastructure;

    @InjectMocks
    private UserGetAdapter userGetAdapter;

    private UUID id1;
    private UUID id2;

    @BeforeEach
    void setUp() {
        this.id1 = UUID.randomUUID();
        this.id2 = UUID.randomUUID();
    }

    @Test
    void shouldReturnListOfUserModelsWhenIsCalledGetAll() {

        List<UserEntity> userEntityList = List.of(
                new UserEntity(this.id1, "papo", "juan leon", "user", "papo123"),
                new UserEntity(this.id2, "felipe", "andres leon", "admin", "felipe123")
        );

        List<UserModel> userModelList = List.of(
                new UserModel(this.id1, "papo", "juan leon", "user", "papo123"),
                new UserModel(this.id2, "felipe", "andres leon", "admin", "felipe123")
        );

        when(this.iUserRepository.findAll()).thenReturn(userEntityList);
        when(this.iMapperUserInfrastructure.toModelList(userEntityList))
                .thenReturn(userModelList);

        List<UserModel> response = this.userGetAdapter.getAll();

        assertEquals(userModelList, response);
        assertEquals(2, response.size());
        assertEquals("papo", response.get(0).userName());
        assertEquals("admin", response.get(1).rol());
        assertEquals(this.id1, response.get(0).id());
        assertEquals(this.id2, response.get(1).id());

        verify(this.iUserRepository).findAll();
        verify(this.iMapperUserInfrastructure).toModelList(userEntityList);
    }

    @Test
    void shouldReturnUserModelWhenIsCalledGetUserNameWithParamUserName() {
        String userName = "papo";

        UserModel userModel = new UserModel(
                this.id1,
                userName,
                "juan leon",
                "user",
                "papo123"
        );

        UserEntity userEntity = new UserEntity(
                this.id1,
                userName,
                "juan leon",
                "user",
                "papo123"
        );

        when(this.iUserRepository.findByUserName(userName)).thenReturn(Optional.of(userEntity));
        when(this.iMapperUserInfrastructure.toModel(userEntity)).thenReturn(userModel);

        UserModel response = this.userGetAdapter.getUserName(userName);

        assertEquals(userModel, response);
        assertEquals(userName, response.userName());
        assertEquals("user", response.rol());
        assertEquals(this.id1, response.id());

        verify(this.iUserRepository).findByUserName(userName);
        verify(this.iMapperUserInfrastructure).toModel(userEntity);
    }

    @Test
    void shouldReturnNotFoundUserExceptionWhenIsCalledGetUserNameAndUserEntityNotExist() {
        String userName = "no existe";

        when(this.iUserRepository.findByUserName(userName)).thenReturn(Optional.empty());

        NotFoundUserException exception = assertThrows(NotFoundUserException.class, () -> {
            this.userGetAdapter.getUserName(userName);
        });

        String expectedMessage = "No se encontro el usuario con el userName: " + userName;
        assertEquals(expectedMessage, exception.getMessage());

        verify(this.iUserRepository).findByUserName(userName);
        verifyNoInteractions(this.iMapperUserInfrastructure);
    }

}