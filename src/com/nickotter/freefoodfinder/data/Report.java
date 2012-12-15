package com.nickotter.freefoodfinder.data;

import java.util.Hashtable;

public class Report {
	
	private String title;
	private String locationDescription;
	private String description;
	private Hashtable<String, Double> location;
	
	public Report() {
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the locationDescription
	 */
	public String getLocationDescription() {
		return locationDescription;
	}
	/**
	 * @param locationDescription the locationDescription to set
	 */
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setLocation(Double lat, Double lon) {
		this.location = new Hashtable<String, Double>();
		this.location.put("lat", lat);
		this.location.put("long", lon);
	}

}
