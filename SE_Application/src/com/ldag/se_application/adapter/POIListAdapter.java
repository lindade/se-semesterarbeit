package com.ldag.se_application.adapter;

import java.util.ArrayList;

import com.ldag.se_application.R;
import com.ldag.se_application.model.POI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class POIListAdapter extends BaseAdapter {

	 private Context context;
	 private POI[] pois;
	     
	 public POIListAdapter(Context context, POI[] pois){
	     this.context = context;
	     this.pois = pois;
	}
	    
	@Override
	public int getCount() {
		return this.pois.length;
	}


	public POI[] getPois() {
		return pois;
	}

	public void setPois(POI[] pois) {
		this.pois = pois;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
			if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater)
	                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.poi_list_item, null);
	        }
	          
	        //ImageView thumbnail = (ImageView) convertView.findViewById(R.id.poi_image);
	        TextView textName = (TextView) convertView.findViewById(R.id.poi_name);
	        TextView textDescription = (TextView) convertView.findViewById(R.id.poi_description);
	        TextView textDate = (TextView) convertView.findViewById(R.id.poi_date);
	          
	        textName.setText(this.pois[position].getName());        
	        textDescription.setText(this.pois[position].getDescription());
	        textDate.setText(this.pois[position].getStartDate() + " - " + this.pois[position].getEndDate());
	         
	        return convertView;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}


