package com.ldag.se_application.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldag.se_application.R;

public class TransportmittelFragment extends Fragment {

	public TransportmittelFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_transportmittel, container, false);
          
        return rootView;
    }
}

