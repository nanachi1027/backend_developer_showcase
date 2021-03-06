package org.cloud.mae.oauth.config;

import org.cloud.mae.model.commons.constants.PermitAllUrl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Mae
 * @Date: 2021/2/23 1:44 上午
 * <p>
 * this resource server config helps add `org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter`
 * this filter which extracts 'access_token' value from request body.
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest request) {
                if (request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) != null){
                    return true;
                }

                // header's authentication begin with Bearer
                String auth = request.getHeader("Authentication");
                if (auth != null) {
                    return auth.startsWith(OAuth2AccessToken.BEARER_TYPE);
                }
                return false;
            }
        }).authorizeRequests()
                .antMatchers(PermitAllUrl.permitAllUrl()).permitAll()
                .anyRequest().authenticated();
    }
}
