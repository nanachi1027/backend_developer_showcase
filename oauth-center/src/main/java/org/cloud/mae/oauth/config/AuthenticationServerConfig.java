/*
package org.cloud.mae.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

*/
/**
 * @Author: Mae
 * @Date: 2021/2/23 1:34 上午
 * <p>
 * authentication server config
 *//*


@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {
    */
/**
     * auth manager
     *//*

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    */
/**
     * retrieve value from jwt or redis.
     * redis in default
     *//*

    @Value("${access_token.store-jwt:false}")
    private boolean storeWithJwt;

    */
/**
     * append user login return json info to current login user context info.
     * false in default
     *//*

    @Value("${access_token.add-userinfo:false}")
    private boolean addUserInfo;

    // todo: add redis auth service here

    // todo: add redis client detail service here

    // token store
    @Bean
    public TokenStore tokenStore() {
        if (storeWithJwt) {
            return new JwkTokenStore(accessTokenConverter());
        }

        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());

        return redisTokenStore;
    }
}
*/
