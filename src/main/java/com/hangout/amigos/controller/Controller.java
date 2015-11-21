package com.hangout.amigos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hangout.amigos.configuration.AppConfiguration;
import com.hangout.amigos.dto.NearByPlaceResult;
import com.hangout.amigos.dto.NearByPlaceWithAnyType;
import com.hangout.amigos.dto.NearByRestaurant;
import com.hangout.amigos.impl.GooglePlaceImpl;


@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/bonvoyage/*")	
@Import(AppConfiguration.class)

/**
 * 
 * @author puneetpopli
 *
 */
public class Controller extends WebMvcConfigurerAdapter {
	
	@Autowired
	GooglePlaceImpl googlePlace;
	
	
	/*
	 * 1. Get NearBy Places
	 */
	@RequestMapping(value="/getplaces/{latitude}/{longitude}/{radius}", method=RequestMethod.GET)
	public ResponseEntity<List<NearByPlaceResult>> getNearByPlace(@PathVariable("latitude") double latitude, 
			@PathVariable("longitude") double longitude, @PathVariable("radius") int radius) {
		
		
		List<NearByPlaceResult> place = googlePlace.getNearByPlaces(latitude, longitude, radius);
		
		return new ResponseEntity<List<NearByPlaceResult>>(place, HttpStatus.OK);
	}
	
	
	/*
	 * 2. Get NearBy Restaurants
	 */
	@RequestMapping(value="/getrestaurant/restaurants/{latitude}/{longitude}/{radius}", method=RequestMethod.GET)
	public ResponseEntity<List<NearByRestaurant>> getNearByRestaurant(@PathVariable("latitude") double latitude, 
			@PathVariable("longitude") double longitude, @PathVariable("radius") int radius) {
		
		List<NearByRestaurant> place = googlePlace.getNearByRestaurant(latitude, longitude, radius);
		
		return new ResponseEntity<List<NearByRestaurant>>(place, HttpStatus.OK);
	}
	
	/*
	 * 3. Get NearBy Places of any type.
	 */
	
	@RequestMapping(value="/getplaces/{latitude}/{longitude}/{radius}/type/{type}", method=RequestMethod.GET)
	public ResponseEntity<List<NearByPlaceWithAnyType>> getNearByPlaceWithAnyType(@PathVariable("latitude") double latitude, 
			@PathVariable("longitude") double longitude, @PathVariable("radius") int radius, @PathVariable("type") String type) {
		
		List<NearByPlaceWithAnyType> place = googlePlace.getNearByPlaceWithAnyType(latitude, longitude, radius, type);
		
		return new ResponseEntity<List<NearByPlaceWithAnyType>>(place, HttpStatus.OK);
	}
	
}

