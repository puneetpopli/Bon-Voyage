package com.hangout.amigos.dto;

import java.util.Collections;
import java.util.List;

import com.hangout.amigos.dto.Place.Geometry;

/**
 * 
 * @author puneetpopli
 *
 */

public class ParticularPlaceDetail {

	private Geometry geometry;

	private  List<Review> reviews = Collections.emptyList();
	
	private String formatted_address;

	private String formatted_phone_number;
	
	private OpeningHours opening_hours;
	
	private String place_id;

	private String vicinity;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
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
	
	
	
	
}
