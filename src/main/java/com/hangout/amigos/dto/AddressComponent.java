package com.hangout.amigos.dto;

import java.util.Collections;
import java.util.Set;

/**
 * 
 * @author puneetpopli
 *
 */

public class AddressComponent {
	
	private String long_name;
	
	private String short_name;
	
	private Set<String> types = Collections.emptySet();

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public Set<String> getTypes() {
		return types;
	}

	public void setTypes(Set<String> types) {
		this.types = types;
	}
	
}
