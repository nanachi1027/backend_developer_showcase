package org.cloud.mae.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Mae
 * @Date: 2021/2/23 1:08 上午
 *
 * oauth center
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OAuthCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthCenterApplication.class, args);
    }
}
