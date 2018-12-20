package com.itmonster.kwai.cloud.auth;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ace on 2017/6/2.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.itmonster.kwai.cloud.auth.mapper")
@RestController
@RefreshScope
public class AuthBootstrap {
    @Value("${spring.userName:kwai}")
    private String userName;
    public static void main(String[] args) {
        SpringApplication.run(AuthBootstrap.class, args);
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello World! My name is "+userName;
    }
}
