package com.hangout.amigos.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


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
        SpringApplication.run(GooglePlacesApplication.class, args);
    }
}
