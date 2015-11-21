package com.hangout.amigos.dto;

import java.util.Collections;
import java.util.List;


/**
 * 
 * @author puneetpopli
 *
 */
public class OpeningHours {
	
	private boolean open_now;
	
	private List<OpenPeriod> periods = Collections.emptyList();
	
	private List<String> weekday_text = Collections.emptyList();
	
	public List<String> getWeekday_text() {
		return weekday_text;
	}

	public void setWeekday_text(List<String> weekday_text) {
		this.weekday_text = weekday_text;
	}

	public List<OpenPeriod> getPeriods() {
		return periods;
	}

	public void setPeriods(List<OpenPeriod> periods) {
		this.periods = periods;
	}

	public boolean isOpen_now() {
		return open_now;
	}

	public void setOpen_now(boolean open_now) {
		this.open_now = open_now;
	}

	

	
}
