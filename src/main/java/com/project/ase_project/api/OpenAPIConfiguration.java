package com.project.ase_project.api;

import io.micrometer.common.KeyValue;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Info information = new Info()
                .title("OPGG-like stats viewer for LoL API")
                .version("1.0")
                .description("Our Advanced Software Engineering project documentation.");

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
