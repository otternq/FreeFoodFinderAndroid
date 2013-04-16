package com.nickotter.freefoodfinder.data;

public class MyLatLng {
	
	protected Double lat;
	protected Double lng;
	
	public MyLatLng(Double tempLat, Double tempLong) {
		this.lat = tempLat;
		this.lng = tempLong;
	}
	
	public void setLat(Double tempLat) {
		this.lat = tempLat;
	}
	
	public void setLng(Double tempLng) {
		this.lng = tempLng;
	}
	
	public Double getLat() {
		return this.lat;
	}
	
	public Double getLng() {
		return this.lng;
	}

}
