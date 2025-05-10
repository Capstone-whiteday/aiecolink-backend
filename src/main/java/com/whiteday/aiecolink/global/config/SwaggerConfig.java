package com.whiteday.aiecolink.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("BearerAuth");

        return new OpenAPI()
                .info(new Info()
                        .title("🚗🔋 Aiecolink API 🚗🔋")
                        .version("1.0")
                        .description("aiecolink api 입니다."))
                .addSecurityItem(securityRequirement)
                .schemaRequirement("BearerAuth", securityScheme);
    }
}