package br.com.casasbahia.crud_h2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI produtosOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API de Produtos")
                .description("CRUD de produtos com Spring Boot e banco H2")
                .version("1.0.0"));
    }
}