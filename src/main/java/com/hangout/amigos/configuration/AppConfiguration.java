package com.hangout.amigos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hangout.amigos.impl.GooglePlaceImpl;


/**
 * 
 * @author puneetpopli
 *
 */

@Configuration
@EnableTransactionManagement
public class AppConfiguration {
	
	@Bean
	public GooglePlaceImpl getGooglePlace(){
		
		return new GooglePlaceImpl();
	}

}
