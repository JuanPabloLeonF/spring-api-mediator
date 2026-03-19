package dev.juanleon.spring_api.common.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API de practica",
                version = "${api.version}",
                contact = @Contact(
                        name = "Juan Pablo Leon Fonseca",
                        email = "fonseca123@gmail.com",
                        url = "https://juanpabloleonf.github.io/portfolio-web/"
                ),
                license = @License(
                        name = "Apche 2.0",
                        url = "https//www.apache.org/license/LICENSE-2.0"
                ),
                termsOfService = "${tos.uri}",
                description = "${api.description}"
        ),
        servers = @Server(
                url = "${api.server.url}",
                description = "Production"
        )
)
@Configuration
public class SwaggerConfiguration {
}
