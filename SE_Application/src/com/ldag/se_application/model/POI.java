package com.ldag.se_application.model;

public class POI {
	
	private String name;
	private String description;
	private String startDate;
	private String endDate;
	private double latitude;
	private double longitude;
	
	
	public POI() {
		super();
	}
	
	public POI(String name, String description, String startDate, String endDate, double latitude, double longitude) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String date) {
		this.startDate = date;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String date) {
		this.endDate = date;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double d) {
		this.latitude = d;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "POI [name=" + name + ", description=" + description
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
}

