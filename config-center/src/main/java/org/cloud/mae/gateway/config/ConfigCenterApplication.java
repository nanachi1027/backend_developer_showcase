package org.cloud.mae.gateway.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: Mae
 * @Date: 2021/2/21 11:59 下午
 * <p>
 * Config Center Application Starter.
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }
}
