package org.cloud.mae.model.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:59 上午
 *
 * system swagger config class.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Gateway Swagger API Doc")
                .apiInfo(new ApiInfoBuilder().title("Gateway Swagger API Doc")
                        .contact(new Contact("Mae,Xie", "", "nanachi1027@163.com")).version("1.0.0").build())
                .select().paths(PathSelectors.any()).build();
    }
}
