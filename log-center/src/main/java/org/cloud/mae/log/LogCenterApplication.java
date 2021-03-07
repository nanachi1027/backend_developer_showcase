package org.cloud.mae.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Mae
 * @Date: 2021/3/8 12:04 上午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LogCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogCenterApplication.class, args);
    }
}
