package org.cloud.mae.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Service Registration Center based on Eureka.
 *
 * @Author: Mae
 * @Date: 2021/2/21 11:26 下午
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterCenterApplication.class, args);
    }
}
