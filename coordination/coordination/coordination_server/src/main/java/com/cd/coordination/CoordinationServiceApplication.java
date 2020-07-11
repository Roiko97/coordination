package com.cd.coordination;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cd.coordination.dao")
public class CoordinationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoordinationServiceApplication.class, args);
    }

}
