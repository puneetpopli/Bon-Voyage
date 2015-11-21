package com.hangout.amigos.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hangout.amigos.controller.Controller;


/**
 * 
 * @author puneetpopli
 *
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class GooglePlacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Controller.class, args);
    }
}
