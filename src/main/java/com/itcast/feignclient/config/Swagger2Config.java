package com.itcast.feignclient.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.itcast.feignclient.pojo.SwaggerProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author zheng.zhang
 * Description 文档配置类
 * @date 2020/9/9 10:48
 * Version 1.0
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@EnableKnife4j
public class Swagger2Config {
    @Autowired
    private SwaggerProperty swaggerProperty;

    @Bean
    public Docket createRestApi() {
        System.out.println(swaggerProperty);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 分组名称
                //.groupName(swaggerProperty.getTitle())
                // 对所有API进行监控
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperty.getBasePackage()))
                // 不显示错误的接口地址
                .paths(PathSelectors.any())
                // 错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    }

    /**
     * 安全上下文。即存储认证授权的相关信息，实际上就是存储"当前用户"账号信息和相关权限
     * @return SecurityContext
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("TOKEN", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("TOKEN", "Authorization", "header");
    }

    /**
     * 创建Swagger页面 信息
     *
     * @return API详情
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperty.getTitle())
                .contact(new Contact(swaggerProperty.getTitle(), swaggerProperty.getLicense(), swaggerProperty.getContact()))
                .version(swaggerProperty.getVersion())
                .license(swaggerProperty.getLicense())
                .licenseUrl(swaggerProperty.getLicenseUrl())
                .termsOfServiceUrl(swaggerProperty.getTermsOfServiceUrl())
                .description(swaggerProperty.getDescription())
                .build();
    }
}
