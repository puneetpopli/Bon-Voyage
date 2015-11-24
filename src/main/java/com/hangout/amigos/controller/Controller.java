package com.hangout.amigos.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hangout.amigos.configuration.AppConfiguration;
import com.hangout.amigos.dto.LoginDTO;
import com.hangout.amigos.dto.NearByPlaceResult;
import com.hangout.amigos.dto.NearByPlaceWithAnyType;
import com.hangout.amigos.dto.NearByRestaurant;
import com.hangout.amigos.dto.ParticularPlaceDetail;
import com.hangout.amigos.dto.PlaceTextSearch;
import com.hangout.amigos.dto.UserDTO;
import com.hangout.amigos.impl.GooglePlaceImpl;
import com.hangout.amigos.impl.LoginImpl;
import com.hangout.amigos.impl.UserOperationImpl;
import com.hangout.amigos.util.HangoutAmigosUtil;


@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/hangoutamigos/*")	
@Import(AppConfiguration.class)

/**
 * 
 * @author puneetpopli
 *
 */
public class Controller extends WebMvcConfigurerAdapter {

	@Autowired
	GooglePlaceImpl googlePlace;

	@Autowired
	UserOperationImpl userOperationImpl;

	@Autowired
	LoginImpl loginImpl;


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

	/*
	 * 4. Get details of a particular place
	 */
	@RequestMapping(value="/getplacedetail/placeid/{placeid}", method=RequestMethod.GET)
	public ResponseEntity<List<ParticularPlaceDetail>> getNearByPlaceWithAnyType(@PathVariable("placeid") String placeId) {

		List<ParticularPlaceDetail> place = googlePlace.getParticularPlaceDetail(placeId);

		return new ResponseEntity<List<ParticularPlaceDetail>>(place, HttpStatus.OK);
	}
	
	
	/*
	 * 5.  Get a restaurant when a user enter query. For e.g restaurants in san francisco
	 */
	@RequestMapping(value="/getrestaurant/restaurants/textsearch/{query}", method=RequestMethod.GET)
	public ResponseEntity<List<PlaceTextSearch>> getNearByRestaurantTextSearch(@PathVariable("query") String  query ) {

		List<PlaceTextSearch> place = googlePlace.getRestaurantBasedOnQuery(query);
		return new ResponseEntity<List<PlaceTextSearch>>(place, HttpStatus.OK);
	}
	
	
	/*
	 * 6. Create User
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO createUser(@RequestBody UserDTO userDto) {
		System.out.println("Inside controller create user");
		
		System.out.println("Name " + userDto.getFirstName());
		
		userDto.setPassword(HangoutAmigosUtil.passwordEncryption(userDto.getPassword()));

		System.out.println("Encrypted Password " + userDto.getPassword());
		

		return userOperationImpl.createUser(userDto);
	}

	/*
	 * 7. Get a user
	 * 
	 */

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getUser/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUser(@PathVariable String userId) {
		return userOperationImpl.getUser(userId);
	}


	/*
	 * 8. Login
	 */

	@RequestMapping("/login")
	@ResponseBody
	private LoginDTO login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
		loginDTO = loginImpl.login(loginDTO);
		
		loginDTO.setPassword(HangoutAmigosUtil.passwordEncryption(loginDTO.getPassword()));
		
		System.out.println("EMail " + loginDTO.getEmail() + " - Pass "+ loginDTO.getPassword() + " - Userid" + loginDTO.getUserId());
		
		response.addCookie(new Cookie("sessionid", loginDTO.getSessionId()));
		response.addCookie(new Cookie("username", loginDTO.getEmail()));
		response.addCookie(new Cookie("userId", Integer.toString(loginDTO.getUserId())));
		return loginDTO;
	}


	/*
	 * 9. Logout
	 */
	@RequestMapping("/logout")
	@ResponseBody
	private boolean logout(HttpServletResponse response) {
		response.addCookie(new Cookie("sessionid", ""));
		response.addCookie(new Cookie("username", ""));
		response.addCookie(new Cookie("userId", ""));
		return true;
	}
}

