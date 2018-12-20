package com.itmonster.kwai.cloud.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ITMonster Kwai
 * @email loqelia_s@sina.com
 * @date 2017年08月25日
 */
@SpringBootApplication
@MapperScan("com.itmonster.kwai.cloud.generator.mapper")
public class GeneratorBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorBootstrap.class, args);
    }
}
