package com.ldag.se_targetapp.model;

import android.content.Context;

public class CustomImage {
	
	private String resourceName;
	private String subtitle;
	private String dateTaken;
	private int imageResourceId;
	
	public CustomImage(String resourceName, String subtitle, String dateTaken, Context context) {
		super();
		this.resourceName = resourceName;
		this.subtitle = subtitle;
		this.dateTaken = dateTaken;
		setImageResourceId(context);
	}

	public CustomImage() {
		super();
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public int getImageResourceId() {
		return imageResourceId;
	}

	public void setImageResourceId(Context context) {
		this.imageResourceId = context.getResources().getIdentifier(this.getResourceName(), "drawable", context.getPackageName());
	}


	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}

	@Override
	public String toString() {
		return "Image [resourceName=" + resourceName + ", subtitle="
				+ subtitle + ", dateTaken=" + dateTaken + "]";
	}

}

