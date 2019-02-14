package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hy on 2019/2/13.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class WendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WendaApplication.class,args);
    }
}
