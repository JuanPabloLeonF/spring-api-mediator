package dev.juanleon.spring_api.user.domain.model;

import java.util.UUID;

public record UserModel(
        UUID id,
        String userName,
        String name,
        String rol,
        String password
) {
}
