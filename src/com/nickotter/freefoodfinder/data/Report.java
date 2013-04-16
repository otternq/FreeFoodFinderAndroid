package com.nickotter.freefoodfinder.data;

import java.util.Hashtable;

import com.google.android.gms.maps.model.LatLng;


public class Report {
	
	private String title;
	private String locationDescription;
	private String description;
	private MyLatLng location;
	
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
	
	public void setLocation(MyLatLng temp) {
		this.location = temp;
	}
	
	public LatLng getLocation() {
		return new LatLng(
			this.location.getLat(),
			this.location.getLng()
		);
	}

}
