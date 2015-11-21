package com.hangout.amigos.dto;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author puneetpopli
 *
 */
public class Place {


	/*
	 * Location
	 */

	public static class Location {

		private double lat;

		private double lng;

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		@Override
		public String toString() {
			return this.getLat() + ", " + this.getLng();
		}


	}

	/*
	 * Geometry
	 */

	public static class Geometry {

		private Location location;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		@Override
		public String toString() {

			return this.getLocation().toString();
		}

	}


	private Geometry geometry;

	private String icon;

	private String id;

	private String name;

	private String place_id;

	private String reference;

	private String vicinity;

	private Set<String> types = Collections.emptySet();

	private String vicinty;

	private Float rating;

	/*
	 * Following fields are required for restaurant
	 */
	
	private OpeningHours opening_hours;
	
	private Integer price_level;
	
	/*
	 * Following fields are required for Particular Place Detail
	 */
	private  List<Review> reviews = Collections.emptyList();

	private  List<AddressComponent> address_components = Collections.emptyList();
	
	private String formatted_address;

	private String formatted_phone_number;
	
	private Integer international_phone_number;

	private Integer utcOffset;

	//This is the URL of the location on google maps
	private URL url;

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

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
	/*----------------------------------------------*/
	
	public OpeningHours getOpening_hours() {
		return opening_hours;
	}

	public void setOpening_hours(OpeningHours opening_hours) {
		this.opening_hours = opening_hours;
	}

	public Integer getPrice_level() {
		return price_level;
	}

	public void setPrice_level(Integer price_level) {
		this.price_level = price_level;
	}

	public Geometry getGeometry() {
		return this.geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getVicinity() {
		return this.vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public Set<String> getTypes() {
		return this.types;
	}

	public void setTypes(Set<String> types) {
		this.types = types;
	}

	public String getVicinty() {
		return this.vicinty;
	}

	public void setVicinty(String vicinty) {
		this.vicinty = vicinty;
	}

	public Float getRating() {
		return this.rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

}
