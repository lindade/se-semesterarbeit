package com.ldag.se_targetapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldag.se_targetapp.R;

public class TravellogScreenFragment extends Fragment {

	public TravellogScreenFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_travellogscreen, container, false);
          
        return rootView;
    }
}
