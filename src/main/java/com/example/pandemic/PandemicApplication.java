package com.example.pandemic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author codejie
 * @since 2022-04-27
 */
@SpringBootApplication
@MapperScan("com.example.pandemic.mapper")
public class PandemicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandemicApplication.class, args);
    }

}
