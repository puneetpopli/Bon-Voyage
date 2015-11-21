package com.hangout.amigos.dto;


import java.util.Date;
import java.util.List;
import se.walkercrou.places.Review.Aspect;


/**
 * 
 * @author puneetpopli
 *
 */
public class Review {

	private List<Aspect> aspects;

	private String author_name;
	
	private String author_url;
	
	private String language;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	private String text;
	
	private Float rating;
	
	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
	private Long time;

	
	
	
	public List<Aspect> getAspects() {
		return this.aspects;
	}

	public void setAspects(List<Aspect> aspects) {
		this.aspects = aspects;
	}
	

	public String getAuthor_name() {
		return author_name;
	}
	
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	
	public String getAuthor_url() {
		return author_url;
	}
	
	public void setAuthor_url(String author_url) {
		this.author_url = author_url;
	}

	
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Long getTime() {
		return time;
	}
	
	public Date setTime(Long time) {
		return new Date(this.getTime());
	}

	
	

}
