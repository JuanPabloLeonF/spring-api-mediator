package dev.juanleon.spring_api.user.infrastructure.api.output.database.repository;

import dev.juanleon.spring_api.user.infrastructure.api.output.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByUserName(String userName);
}
