package com.mredust.mredustusersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mredust.mredustusersystem.mapper")
public class MredustUserSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MredustUserSystemApplication.class, args);
    }
    
}
