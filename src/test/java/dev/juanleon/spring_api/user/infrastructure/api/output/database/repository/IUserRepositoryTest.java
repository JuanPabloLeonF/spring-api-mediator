package dev.juanleon.spring_api.user.infrastructure.api.output.database.repository;

import dev.juanleon.spring_api.user.infrastructure.api.output.database.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IUserRepositoryTest {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindUserByUserName() {
        UserEntity user = new UserEntity();
        user.setUserName("juanleon");
        user.setName("juan pablo leon fonseca");
        user.setRol("user");
        user.setPassword("123456");

        entityManager.persistAndFlush(user);

        Optional<UserEntity> found = iUserRepository.findByUserName("juanleon");

        assertTrue(found.isPresent(), "El usuario debería estar presente");
        assertEquals("juanleon", found.get().getUserName());
        assertEquals("user", user.getRol());
    }

    @Test
    void shouldReturnEmptyWhenUserNameDoesNotExist() {
        Optional<UserEntity> found = iUserRepository.findByUserName("nonexistent");
        assertTrue(found.isEmpty());
    }
}