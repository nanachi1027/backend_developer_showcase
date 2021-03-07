package org.cloud.mae.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Mae
 * @Date: 2021/2/22 1:12 上午
 */

@Slf4j
@RestController("/service-instances")
public class ServiceInstanceController {
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * We can get all the services info by this API.
     */
    @GetMapping
    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        List<String> services = discoveryClient.getServices();
        services.forEach(serviceId -> {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            map.put(serviceId, instances);
        });

        return map;
    }
}
