package org.cloud.mae.oauth.feign;

import org.cloud.mae.model.user.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Mae
 * @Date: 2021/2/23 1:08 上午
 */
@FeignClient("user-center")
public interface UserClient {
    @GetMapping(value = "/users-anon/internal", params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    @GetMapping("/wechat/login-check")
    public void wechatLoginCheck(@RequestParam("tempCode") String tempCode, @RequestParam("openid") String openid);
}
