package org.cloud.mae.model.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:55 上午
 */
@FeignClient("oauth-center")
public interface Oauth2Client {

    /**
     * retrieve access_token
     *
     * @param params
     * @return
     */
    @PostMapping(path = "/oauth/token")
    Map<String, Object> postAccessToken(@RequestParam Map<String, String> params);

    /**
     * remove access_token && refresh access_token
     *
     * @param accessToken
     */
    @DeleteMapping(path = "/remove_token")
    void removeAccessToken(@RequestParam("access_token") String accessToken);
}
