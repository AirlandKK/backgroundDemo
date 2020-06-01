package com.peesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages="com.peesystem")
/*@MapperScan(basePackages = "com.peesystem.mapper", markerInterface = MyMapper.class)*/
public class PeeSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(PeeSystemApplication.class, args);
    }


}

