package org.cloud.mae.oauth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Mae
 * @Date: 2021/3/4 9:18 上午
 */
@FeignClient("notification-center")
public interface SmsClient {
    @GetMapping(value = "/notificatioin-anon/internal/phone", params = {"key", "code"})
    public String matcheCodeAndGetPhone(@RequestParam("key") String key, @RequestParam("code") String code,
                                        @RequestParam(value = "delete", required = false) Boolean delete,
                                        @RequestParam(value = "second", required = false) Integer second);
}
