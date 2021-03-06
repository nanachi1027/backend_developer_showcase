package org.cloud.mae.oauth.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: Mae
 * @Date: 2021/2/23 1:14 上午
 * <p>
 * swagger document config class.
 */
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("OAuthCenter Swagger API Doc.")
                .apiInfo(new ApiInfoBuilder().title("OAuthCenter Swagger API Doc.")
                        .contact(new Contact("Mae", "", "nanachi1027@163.com")).version("1.0.0").build())
                .select().paths(PathSelectors.any()).build();
    }
}
