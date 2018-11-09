package com.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by KaBu on 2018/11/9.
 */
@SpringBootApplication
@EnableEurekaServer
public class AppEureka2 {


    public static void main(String[] args){
        SpringApplication.run(AppEureka2.class,args);
    }
}
