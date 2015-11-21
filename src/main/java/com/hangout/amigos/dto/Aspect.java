package com.hangout.amigos.dto;


/**
 * @author puneetpopli
 */

/**
 * Represents an certain aspect of a review such as "quality" or "service".
 */
public class Aspect {

	private Float rating;

	private String type;
	
	/**
	 * Returns the rating between 1 and 3
	 *
	 * @return rating
	 */
	public Float getRating() {
		return rating;
	}


	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	
	/**
	 * Returns the type of the aspect.
	 *
	 * @return aspect type
	 */
	public String getType() {
		return type;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}

	
}
