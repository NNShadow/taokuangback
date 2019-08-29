package com.flying.taokuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.flying.taokuang.dao")
@SpringBootApplication
public class TaokuangApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaokuangApplication.class, args);
    }

}
