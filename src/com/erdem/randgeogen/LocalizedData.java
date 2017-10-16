package com.erdem.randgeogen;

import java.util.Date;

public class LocalizedData {

	private String id;
	private Date date;
	private double lat;
	private double lng;
	
	public LocalizedData(){
		
        this.id = RandomUtil.randomAlphanumeric(10);
	}
	
	public LocalizedData(Date date,double lat,double lng){
		this.date = date;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(long lng) {
		this.lng = lng;
	}
	
	
}
