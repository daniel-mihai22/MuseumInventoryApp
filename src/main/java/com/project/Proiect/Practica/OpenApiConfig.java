package com.project.Proiect.Practica;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Daniel Mihai",
                        email = "dmihai6@gmail.com",
                        url = "https://github.com/daniel-mihai22"
                ),
                description = "OpenApi documentatie pentru proiect.",
                title = "OpenApi documentatie - Proiect Practica",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)

public class OpenApiConfig {
}
