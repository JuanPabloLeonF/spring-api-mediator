package dev.juanleon.spring_api.user.infrastructure.api.output.configuration;

import dev.juanleon.spring_api.user.domain.persistence.get.IUserGetPersistence;
import dev.juanleon.spring_api.user.domain.service.get.IUserGetService;
import dev.juanleon.spring_api.user.domain.useCase.get.UserGetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurationUser {

    @Bean
    public IUserGetService iUserGetService(IUserGetPersistence iUserGetPersistence) {
        return new UserGetService(iUserGetPersistence);
    }
}
