package com.carryit.base.besttmwuu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.carryit.base.besttmwuu.dao")
public class BesttmwuuApplication {

    public static void main(String[] args) {
        SpringApplication.run(BesttmwuuApplication.class, args);
    }
}
