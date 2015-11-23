package com.hangout.amigos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hangout.amigos.impl.GooglePlaceImpl;
import com.hangout.amigos.impl.LoginImpl;
import com.hangout.amigos.impl.UserOperationImpl;


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
	
	@Bean
	public UserOperationImpl userCreation() {
		return new UserOperationImpl();
	}
	
	@Bean
	public LoginImpl loginOperation() {
		return new LoginImpl();
	}

}
