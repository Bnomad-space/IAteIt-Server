package com.bnomad.IAteIt.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(getInfo());
    }

    public Info getInfo() {
        Info info = new Info();
        info.setTitle("IAteIt");
        info.setDescription("IAteIt의 API 스펙 알아보기");
        return info;
    }

}
