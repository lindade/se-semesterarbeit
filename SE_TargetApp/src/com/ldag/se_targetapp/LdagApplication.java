package com.ldag.se_targetapp;

import android.app.Application;

public class LdagApplication extends Application {
	
	private static LdagApplication instance;
	private static SQLHelper sql;

	@Override
	public void onCreate() {
		super.onCreate();
		sql = new SQLHelper(this);
		instance = this;
		// Initialize the singletons so their instances
		// are bound to the application process.
		//initSingletons();
	}

	public static LdagApplication getInstance(){
		return instance;
	}
	
	public static SQLHelper getSQLHelper(){
		return sql;
	}

	/*
	protected void initSingletons() {
		// Initialize the instance of any singleton
		MySingleton.initInstance();
	}*/


}
