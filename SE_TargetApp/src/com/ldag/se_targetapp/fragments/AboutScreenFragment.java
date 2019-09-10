package com.ldag.se_targetapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ldag.se_targetapp.R;

public class AboutScreenFragment extends Fragment {

	public AboutScreenFragment(){}
    
	public static final String URL="http://www.auswaertiges-amt.de/DE/Laenderinformationen/01-Reisewarnungen-Liste_node.html"; 
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
    	super.onCreateView(inflater, container, savedInstanceState);
		WebView web = new WebView(getActivity());
		web.loadUrl(URL);
		return web;

    }
}
