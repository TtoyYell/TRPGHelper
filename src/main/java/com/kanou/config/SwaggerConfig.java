package com.kanou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/6/10 20:23
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kanou.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置apiInfo
     * */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "TRPGHelper的API文档"
                ,""
                ,"1.0"
                ,""
                ,new Contact("", "", "")
                ,""
                ,""
                ,new ArrayList()
                );
    }

}
