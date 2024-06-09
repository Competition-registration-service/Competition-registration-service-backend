package ru.vsu.cs.sakovea.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CRS Api",
                description = "CRS", version = "0.0.1",
                contact = @Contact(
                        name = "Саков Евгений",
                        email = "glavnii.homa@gmail.com"
                )
        ),
        servers = { //http://localhost:8080/swagger-ui/index.html
                @Server(url = "http://localhost:8080", description = "сервер для разработки"),
        }
)
public class SwaggerConfig {
}
