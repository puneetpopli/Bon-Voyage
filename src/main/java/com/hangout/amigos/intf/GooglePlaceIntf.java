package com.hangout.amigos.intf;

import java.util.List;

import com.hangout.amigos.dto.NearByPlaceResult;
import com.hangout.amigos.dto.NearByPlaceWithAnyType;
import com.hangout.amigos.dto.NearByRestaurant;

public interface GooglePlaceIntf {

	public List<NearByPlaceResult> getNearByPlaces(double latitude, double longitude, int radius);
	
	public List<NearByRestaurant> getNearByRestaurant(double latitude, double longitude, int radius);
	
	public List<NearByPlaceWithAnyType> getNearByPlaceWithAnyType(double latitude, double longitude, int radius, String type);
	
}
