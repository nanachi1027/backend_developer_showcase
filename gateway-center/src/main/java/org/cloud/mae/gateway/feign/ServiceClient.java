package org.cloud.mae.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:13 上午
 */

@FeignClient("service-center")
public interface ServiceClient {

    @GetMapping("/internal/ips")
    Set<String> findAllBlackIPs(@RequestParam("params") Map<String, Object> params);
}
