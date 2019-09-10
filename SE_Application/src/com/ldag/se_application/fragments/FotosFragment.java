package com.ldag.se_application.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldag.se_application.R;

public class FotosFragment extends Fragment {

	public FotosFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_fotos, container, false);
          
        return rootView;
    }
}

