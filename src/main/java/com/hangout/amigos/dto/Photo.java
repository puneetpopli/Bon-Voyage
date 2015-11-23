package com.hangout.amigos.dto;

import java.util.Collections;
import java.util.List;

public class Photo {
	
	private List<MapUrl> html_attributions = Collections.emptyList();

	public List<MapUrl> getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(List<MapUrl> html_attributions) {
		this.html_attributions = html_attributions;
	}
	
}
