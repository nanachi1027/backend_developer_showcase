package org.cloud.mae.oauth.feign;

import org.cloud.mae.model.log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: Mae
 * @Date: 2021/3/4 9:17 上午
 */
@FeignClient
public interface LogClient {
    @PostMapping("/logs-anon/internal")
    void save(@RequestBody Log log);
}
