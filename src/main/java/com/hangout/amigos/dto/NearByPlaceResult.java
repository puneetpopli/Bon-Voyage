package com.hangout.amigos.dto;

import java.util.Collections;
import java.util.Set;

import com.hangout.amigos.dto.Place.Geometry;

/**
 * 
 * @author puneetpopli
 *
 */
public class NearByPlaceResult {

	private Geometry geometry;
	
	private String icon;

	private String id;

	private String name;

	private String place_id;


	private String reference;

	private String vicinity;

	private Set<String> types = Collections.emptySet();

	private Float rating;

	public String getIcon() {
		return icon;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
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


	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getVicinity() {
		return vicinity;
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

	public Float getRating() {
		return this.rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	
}
