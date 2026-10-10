package com.hydp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@EnableCaching
@MapperScan({"com.hydp.mapper","com.like.mapper"})
@SpringBootApplication(scanBasePackages = {"com"})
public class HyDianPingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyDianPingApplication.class, args);
    }

}
