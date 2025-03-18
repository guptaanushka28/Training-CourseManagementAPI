package com.training.center.config;


import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TrainingCenter API",
                version = "1.0",
                description = "API documentation for Training Center "
        )
)
public class SwaggerConfig {
}

