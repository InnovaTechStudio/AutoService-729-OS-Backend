package com.autoserviceos.api.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * Defines the global metadata for the AutoServiceOS API.
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI autoServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoServiceOS API")
                        .description("AutoServiceOS Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("AutoServiceOS Platform Wiki Documentation")
                        .url("https://autoserviceos.wiki.github.io/docs"));
    }
}