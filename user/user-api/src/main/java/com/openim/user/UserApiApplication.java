package com.openim.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.openim.user.feign")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.openim.**.mapper*")
public class UserApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

}
