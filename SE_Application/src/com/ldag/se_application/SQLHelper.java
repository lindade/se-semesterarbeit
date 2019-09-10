package com.ldag.se_application;

import com.ldag.se_application.model.CustomImage;
import com.ldag.se_application.model.POI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

	private static final String POIS_TABLE = "pois_table";
	private static final String CUSTOMIMAGES_TABLE = "customImages_table";

	public SQLHelper(Context context) {
		super(context, "ldag.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "
				+ POIS_TABLE
				+ "(lat DOUBLE, lng DOUBLE, dateStart TEXT, dateEnd TEXT, placename TEXT, description TEXT)";
		db.execSQL(sql);
		
		sql = "CREATE TABLE " 
				+ CUSTOMIMAGES_TABLE
				+ "(resourceName TEXT, subtitle TEXT, dateTaken TEXT)";
		db.execSQL(sql);
		
	}

	public void deleteAll(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + POIS_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CUSTOMIMAGES_TABLE);
		onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		deleteAll(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		deleteAll(db);
	}


	//START POIS
	public synchronized POI[] readPois() {
		
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.query(POIS_TABLE, null, null, null, null, null, null);
		POI[] items = new POI[c.getCount()];
		for(int i=0;i<c.getCount();i++){
			c.moveToPosition(i);
			items[i]=readPoisFromCursor(db, c);
		}
		c.close();
		return items;
	}

	
	private POI readPoisFromCursor(SQLiteDatabase db, Cursor c){
		POI item = new POI();
		item.setName(c.getString(c.getColumnIndexOrThrow("placename")));
		item.setLatitude(c.getDouble(c.getColumnIndexOrThrow("lat")));
		item.setLongitude(c.getDouble(c.getColumnIndexOrThrow("lng")));
		item.setStartDate(c.getString(c.getColumnIndexOrThrow("dateStart")));
		item.setEndDate(c.getString(c.getColumnIndexOrThrow("dateEnd")));
		item.setDescription(c.getString(c.getColumnIndexOrThrow("description")));
		return item;
	}
	
		
	public synchronized void writePois(POI[] items) {
		if (items == null) {
			return;
		}
		SQLiteDatabase db = getWritableDatabase();
		db.delete(POIS_TABLE, null, null);
		for (POI item : items) {
			ContentValues values = new ContentValues();
			values.put("placename", item.getName());
			values.put("lat", item.getLatitude());
			values.put("lng", item.getLongitude());
			values.put("dateStart", item.getStartDate());
			values.put("dateEnd", item.getEndDate());
			values.put("description", item.getDescription());
			db.insertOrThrow(POIS_TABLE, null, values);
	
		}
	}
	
	public synchronized void writePoi(POI item) {
		if (item == null) {
			return;
		}
		SQLiteDatabase db = getWritableDatabase();
		//db.delete(POIS_TABLE, null, null);
		
			ContentValues values = new ContentValues();
			values.put("placename", item.getName());
			values.put("lat", item.getLatitude());
			values.put("lng", item.getLongitude());
			values.put("dateStart", item.getStartDate());
			values.put("dateEnd", item.getEndDate());
			values.put("description", item.getDescription());
			db.insertOrThrow(POIS_TABLE, null, values);
	}
	
	//END POIS

	//START IMAGE

	public synchronized CustomImage[] readCustomImage(){
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(CUSTOMIMAGES_TABLE, null, null, null, null, null, null);
		CustomImage[] items = new CustomImage[c.getCount()];
		for(int i=0;i<c.getCount();i++){
			c.moveToPosition(i);
			CustomImage item = new CustomImage();
			item.setResourceName(c.getString(c.getColumnIndexOrThrow("resourceName")));
			item.setSubtitle(c.getString(c.getColumnIndexOrThrow("subtitle")));
			item.setDateTaken(c.getString(c.getColumnIndexOrThrow("dateTaken")));
			items[i]=item;
		}
		c.close();
		return items;
	}
	
	public synchronized void writeNews(CustomImage[] items){
		if(items == null){
			return;
		}
		SQLiteDatabase db = getWritableDatabase();
		db.delete(CUSTOMIMAGES_TABLE, null, null);
		for(CustomImage item : items){
			ContentValues values = new ContentValues();
			values.put("resourceName", item.getResourceName());
			values.put("subtitle", item.getSubtitle());
			values.put("dateTaken", item.getDateTaken());
			db.insertOrThrow(CUSTOMIMAGES_TABLE, null, values);
		}
	}
	
	//END IMAGE
	
}

