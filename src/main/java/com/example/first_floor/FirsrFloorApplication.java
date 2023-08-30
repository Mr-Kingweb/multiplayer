package com.example.first_floor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * firsr地板应用程序
 *
 * @author JJ_un
 * @date 2023/08/07
 */
@MapperScan("com.example.first_floor.generator.mapper")
@SpringBootApplication
public class FirsrFloorApplication {


    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(FirsrFloorApplication.class, args);
    }

}
