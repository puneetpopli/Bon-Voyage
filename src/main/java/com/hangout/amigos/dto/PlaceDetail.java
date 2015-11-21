package com.hangout.amigos.dto;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import com.hangout.amigos.dto.Place.Geometry;


/**
 * 
 * @author puneetpopli
 *
 */
public class PlaceDetail {

	private Geometry geometry;
	
	private  List<String> types = Collections.emptyList();

	private  List<Review> reviews = Collections.emptyList();

	private  List<AddressComponent> address_components = Collections.emptyList();
	
	private String formatted_address;

	private String formatted_phone_number;
	
	private OpeningHours opening_hours;
	
	private Integer international_phone_number;

	private Integer utcOffset;
	
	private String place_id;

	private String vicinity;
	
	//This is the URL of the location on google maps
	private URL url;

	
	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<AddressComponent> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(List<AddressComponent> address_components) {
		this.address_components = address_components;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}

	public void setFormatted_phone_number(String formatted_phone_number) {
		this.formatted_phone_number = formatted_phone_number;
	}

	public OpeningHours getOpening_hours() {
		return opening_hours;
	}

	public void setOpening_hours(OpeningHours opening_hours) {
		this.opening_hours = opening_hours;
	}

	public Integer getInternational_phone_number() {
		return international_phone_number;
	}

	public void setInternational_phone_number(Integer international_phone_number) {
		this.international_phone_number = international_phone_number;
	}

	public Integer getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(Integer utcOffset) {
		this.utcOffset = utcOffset;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	

}
