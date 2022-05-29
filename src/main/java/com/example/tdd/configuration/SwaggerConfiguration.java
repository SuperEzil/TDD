package com.example.tdd.configuration;

import com.example.tdd.TddApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage(TddApplication.class.getPackageName()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo());
    }


    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Sample TDD API")
                .version("0.0.1")
                .description("API for basic TDD for Sample")
                .contact(new Contact("Ezil", "https://github.com/SuperEzil/TDD", "devsangtae@gmail.com"))
                .license("Apache License Version 2.0")
                .build();
    }
}
