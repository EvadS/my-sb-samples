package com.se.sample.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.core.env.Environment;

@Configuration
public class SwaggerConfig {

    private final Environment environment;

    @Value("${spring.application.version}")
    private String buildVersion;

    @Value("${spring.application.description}")
    private String description;

    @Value("${spring.application.name}")
    private String applicationName;

    public SwaggerConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        // Проверка активного профиля
        String[] activeProfiles = environment.getActiveProfiles();

        return new OpenAPI()
                .info(new Info().title(applicationName )
                        .description(description)
                        .version(buildVersion  + ", profile: " + String.join(", ", activeProfiles))

                )
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"))
                .components(new Components().addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
                        .name("JavaInUseSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("basic")));
    }
}