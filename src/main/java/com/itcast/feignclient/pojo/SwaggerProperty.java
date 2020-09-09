package com.itcast.feignclient.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zheng.zhang
 * Description swagger配置文件
 * @date 2020/9/9 14:56
 * Version 1.0
 */
@Data
@Component
@PropertySource(value = "classpath:swagger.yml", ignoreResourceNotFound = true, encoding = "utf-8")
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperty {
    @Value("${title}")
    private String title;

    @Value("${contact}")
    private String contact;

    @Value("${projectVersion}")
    private String version;

    @Value("${license}")
    private String license;

    @Value("${licenseUrl}")
    private String licenseUrl;

    @Value("${termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${description}")
    private String description;

    @Value("${basePackage}")
    private String basePackage;
}
