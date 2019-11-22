package com.fjl.sbmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fjl.sbmp.dao")
public class SbmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmpApplication.class, args);
    }

}
