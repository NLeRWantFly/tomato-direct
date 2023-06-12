package com.wang.tomatodirect;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("com.wang.tomatodirect.dao")
public class TomatoDirectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomatoDirectApplication.class, args);
    }

}
