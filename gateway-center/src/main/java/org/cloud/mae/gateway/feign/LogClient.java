package org.cloud.mae.gateway.feign;

import org.cloud.mae.api.log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:54 上午
 */
@FeignClient("log-center")
public interface LogClient {
    @PostMapping("/internal")
    void save(@RequestBody Log log);
}
