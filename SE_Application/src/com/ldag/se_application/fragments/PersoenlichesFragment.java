package com.ldag.se_application.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldag.se_application.R;

public class PersoenlichesFragment extends Fragment {

	public PersoenlichesFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_persoenliches, container, false);
          
        return rootView;
    }
}

