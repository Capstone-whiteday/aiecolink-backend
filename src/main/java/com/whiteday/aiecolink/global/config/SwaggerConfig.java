package com.whiteday.aiecolink.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("🚗🔋 Aiecolink API 🚗🔋")
                        .version("1.0")
                        .description("aiecolink api 입니다."));

    }
}
