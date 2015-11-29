package com.hangout.amigos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hangout.amigos.dto.NearByPlaceResult;
import com.hangout.amigos.dto.NearByPlaceWithAnyType;
import com.hangout.amigos.dto.NearByRestaurant;
import com.hangout.amigos.dto.ParticularPlaceDetail;
import com.hangout.amigos.dto.Place;
import com.hangout.amigos.dto.PlaceDetail;
import com.hangout.amigos.dto.PlaceDetailResult;
import com.hangout.amigos.dto.PlaceTextSearch;
import com.hangout.amigos.dto.Result;
import com.hangout.amigos.intf.GooglePlaceIntf;


/**
 * 
 * @author puneetpopli
 *
 */

@Component
public class GooglePlaceImpl implements GooglePlaceIntf{

	private static String[] placeIdArray;

	private static String[] placeIdOfAnyType;

	private static final String NEARBY_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

	private static final String DETAIL_URL = "https://maps.googleapis.com/maps/api/place/details/json";

	private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";

	//private static String apiKey="AIzaSyADd_jadsjrgHfNgY7XXXooYN8QLRrBWZA";
	
	//Gaurav's key
	private static String apiKey = "AIzaSyDM_okhmwbJ3hiDkzLMtpl0yBKy5mivusc";


	/*
	 * 1. It will return the near by places without any type.
	 */

	/**
	 * 
	 * @param latitude - latitude of the location. The point around which you wish to retrieve place information.
	 * @param longitude - longitude of the location. The point around which you wish to retrieve place information.
	 * @param radius - The distance (in meters) within which to return place results.
	 * @return - an arraylist with the details of near by places.
	 */
	@Override
	public List<NearByPlaceResult> getNearByPlaces(double latitude, double longitude, int radius) {

		List<NearByPlaceResult> nearByPlacesList = new ArrayList<NearByPlaceResult>();

		RestTemplate restTemplate = new RestTemplate();
		Result results;

		String placeUrl = NEARBY_SEARCH_URL+"?location="+latitude+", "+longitude+"&radius="+radius+"&key="+apiKey;
		System.out.println(placeUrl);

		results = restTemplate.getForObject(placeUrl, Result.class);

		for(Place places : results.getResults()) {

			NearByPlaceResult nearByPlace = new NearByPlaceResult();
			nearByPlace.setGeometry(places.getGeometry());
			nearByPlace.setIcon(places.getIcon());
			nearByPlace.setId(places.getId());
			nearByPlace.setName(places.getName());
			nearByPlace.setPlace_id(places.getPlace_id());
			nearByPlace.setReference(places.getReference());
			nearByPlace.setVicinity(places.getVicinity());
			nearByPlace.setTypes(places.getTypes());
			nearByPlace.setRating(places.getRating());

			nearByPlacesList.add(nearByPlace);
		}


		return nearByPlacesList;
	}


	/*
	 * 2. It will return the near by RESTAURANTS
	 */

	/**
	 * 
	 * @param latitude - latitude of the location. The point around which you wish to retrieve restaurant.
	 * @param longitude - longitude of the location. The point around which you wish to retrieve restaurant.
	 * @param radius - The distance (in meters) within which to return restaurant results.
	 * @return - an arraylist with the details of near by restaruants.
	 */
	@Override
	public List<NearByRestaurant> getNearByRestaurant(double latitude, double longitude, int radius) {


		List<NearByRestaurant> nearByRestaurantList = new ArrayList<NearByRestaurant>();

		RestTemplate restTemplate = new RestTemplate();
		Result results;

		String placeUrl = NEARBY_SEARCH_URL+"?location="+latitude+", "+longitude+"&radius="+radius+"&type=restaurant"+"&key="+apiKey;
		System.out.println(placeUrl);

		results = restTemplate.getForObject(placeUrl, Result.class);

		/*
		 * - This array will store only the place_id of all the restaurant
		 * - Size of the array is set to the size of result array returned as JSON response
		 */
		placeIdArray = new String[results.getResults().length];
		int count = 0;

		for(Place places : results.getResults()) {

			NearByRestaurant nearByRestaurant = new NearByRestaurant();
			nearByRestaurant.setGeometry(places.getGeometry());
			nearByRestaurant.setIcon(places.getIcon());
			nearByRestaurant.setId(places.getId());
			nearByRestaurant.setName(places.getName());
			nearByRestaurant.setPlace_id(places.getPlace_id());
			nearByRestaurant.setReference(places.getReference());
			nearByRestaurant.setVicinity(places.getVicinity());
			nearByRestaurant.setTypes(places.getTypes());
			nearByRestaurant.setRating(places.getRating());
			nearByRestaurant.setOpening_hours(places.getOpening_hours());
			nearByRestaurant.setPrice_level(places.getPrice_level());

			nearByRestaurantList.add(nearByRestaurant);

			//Inserting place_id in the array
			placeIdArray[count] = places.getPlace_id();
			count++;
		}

		/*
		 * Checking if the place_id is stored in the array or not. Check console for the result
		 */
		for(int i=0; i<count; i++) {
			System.out.println("Place Id Array " + placeIdArray[i]);
		}
		return nearByRestaurantList;
	}

	/*
	 * 3. It will return the near by place with ANY type. For e.g. type = atm, hotels, airport etc.
	 */
	/**
	 * 
	 * @param latitude - latitude of the location. The point around which you wish to retrieve a place with ANY TYPE.
	 * @param longitude - longitude of the location. The point around which you wish to retrieve a place with ANY TYPE.
	 * @param radius - The distance (in meters) within which to return restaurant results.
	 * @param type - Type of the place we want. For e.g. ATM, Florist etc.
	 * @return - an arraylist with the details of the place.
	 */
	@Override
	public List<NearByPlaceWithAnyType> getNearByPlaceWithAnyType(double latitude, double longitude, int radius, String type) {


		List<NearByPlaceWithAnyType> nearByPlaceWithAnyTypeList = new ArrayList<NearByPlaceWithAnyType>();

		RestTemplate restTemplate = new RestTemplate();
		Result results;

		String placeUrl = NEARBY_SEARCH_URL+"?location="+latitude+", "+longitude+"&radius="+radius+"&type="+type+"&key="+apiKey;
		System.out.println(placeUrl);

		results = restTemplate.getForObject(placeUrl, Result.class);

		/*
		 * - This array will store only the place_id of all the restaurant
		 * - Size of the array is set to the size of result array returned as JSON response
		 */
		placeIdOfAnyType = new String[results.getResults().length];
		int count = 0;

		for(Place places : results.getResults()) {

			NearByPlaceWithAnyType nearByplaceType = new NearByPlaceWithAnyType();
			nearByplaceType.setGeometry(places.getGeometry());
			nearByplaceType.setIcon(places.getIcon());
			nearByplaceType.setId(places.getId());
			nearByplaceType.setName(places.getName());
			nearByplaceType.setPlace_id(places.getPlace_id());
			nearByplaceType.setReference(places.getReference());
			nearByplaceType.setVicinity(places.getVicinity());
			nearByplaceType.setTypes(places.getTypes());
			nearByplaceType.setRating(places.getRating());
			nearByplaceType.setPrice_level(places.getPrice_level());

			nearByPlaceWithAnyTypeList.add(nearByplaceType);

			//Inserting place_id in the array
			placeIdOfAnyType[count] = places.getPlace_id();
			count++;
		}

		/*
		 * Checking if the place_id is stored in the array or not. Check console for the result
		 */
		for(int i=0; i<count; i++) {
			System.out.println("Place Id Array " + placeIdOfAnyType[i]);
		}

		return nearByPlaceWithAnyTypeList;
	}

	/*
	 *4. Details of a particular place 
	 */
	@Override
	public List<ParticularPlaceDetail> getParticularPlaceDetail(String placeId) {


		List<ParticularPlaceDetail> placeDetailList = new ArrayList<ParticularPlaceDetail>();

		RestTemplate restTemplate = new RestTemplate();
		//PlaceDetailResult result;

		PlaceDetailResult result;

		//https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&key=AIzaSyADd_jadsjrgHfNgY7XXXooYN8QLRrBWZA
		String placeUrl = DETAIL_URL+"?placeid="+placeId+"&key="+apiKey;
		System.out.println(placeUrl);

		result = restTemplate.getForObject(placeUrl, PlaceDetailResult.class);


		PlaceDetail placeDetail = result.getResult(); 

		ParticularPlaceDetail particularPlaceDetail = new ParticularPlaceDetail();
		particularPlaceDetail.setGeometry(placeDetail.getGeometry());
		particularPlaceDetail.setFormatted_address(placeDetail.getFormatted_address());
		particularPlaceDetail.setOpening_hours(placeDetail.getOpening_hours());
		particularPlaceDetail.setReviews(placeDetail.getReviews());
		particularPlaceDetail.setPlace_id(placeDetail.getPlace_id());
		particularPlaceDetail.setVicinity(placeDetail.getVicinity());

		placeDetailList.add(particularPlaceDetail);


		return placeDetailList;
	}

	/*
	 * 5. Get a restaurant when a user enter query. For e.g restaurants in san francisco
	 */

	@Override
	public List<PlaceTextSearch> getRestaurantBasedOnQuery(String query) {

		List<PlaceTextSearch> nearByRestaurantList = new ArrayList<PlaceTextSearch>();

		RestTemplate restTemplate = new RestTemplate();
		Result results;

		String placeUrl = TEXT_SEARCH_URL+"?query="+query+"&key="+apiKey;
		System.out.println(placeUrl);

		results = restTemplate.getForObject(placeUrl, Result.class);

		/*
		 * - This array will store only the place_id of all the restaurant
		 * - Size of the array is set to the size of result array returned as JSON response
		 */
		placeIdArray = new String[results.getResults().length];
		int count = 0;

		for(Place places : results.getResults()) {

			PlaceTextSearch nearByRestaurant = new PlaceTextSearch();
			nearByRestaurant.setGeometry(places.getGeometry());
			nearByRestaurant.setIcon(places.getIcon());
			nearByRestaurant.setId(places.getId());
			nearByRestaurant.setName(places.getName());
			nearByRestaurant.setPlace_id(places.getPlace_id());
			nearByRestaurant.setReference(places.getReference());
			nearByRestaurant.setFormatted_address(places.getFormatted_address());
			nearByRestaurant.setTypes(places.getTypes());
			nearByRestaurant.setRating(places.getRating());
			nearByRestaurant.setOpening_hours(places.getOpening_hours());
			nearByRestaurant.setPrice_level(places.getPrice_level());
		//	nearByRestaurant.setPhotos(places.getPhotos());
			nearByRestaurantList.add(nearByRestaurant);

			//Inserting place_id in the array
			placeIdArray[count] = places.getPlace_id();
			count++;
		}
		return nearByRestaurantList;
	}
}