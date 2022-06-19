package com.example.tdd.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("tdd-public")
                .pathsToMatch("/**")
                .build();
    }


    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI().info(
                new Info().title("Sample TDD API")
                        .version("0.0.1")
                        .description("API for basic TDD for Sample")
                        .contact(new Contact().name("DevSangtae").email("devsangtae@gmail.com"))
                        .license(new License().name("Apache License Version 2.0").url("https://namu.wiki/w/%EC%95%84%ED%8C%8C%EC%B9%98%20%EB%9D%BC%EC%9D%B4%EC%84%A0%EC%8A%A4")))

                        .externalDocs(new ExternalDocumentation()
                                .description("springdoc-openapi")
                                .url("https://springdoc.org"));
    }
}
