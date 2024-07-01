package com.hkkim.webservice.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "투개더 API 명세서",
                description = "COMP322-team12 투개더 API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi chatOpenApi() {

        String[] paths = {"/api/v1/**"};

        return GroupedOpenApi.builder()
                .group("투개더 API v1")  // 그룹 이름을 설정한다.
                .pathsToMatch(paths)     // 그룹에 속하는 경로 패턴을 지정한다.
                .build();
    }
}