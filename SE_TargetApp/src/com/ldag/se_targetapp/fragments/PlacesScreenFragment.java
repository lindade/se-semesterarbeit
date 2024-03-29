package com.ldag.se_targetapp.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ldag.se_targetapp.LdagApplication;
import com.ldag.se_targetapp.R;
import com.ldag.se_targetapp.adapter.POIListAdapter;
import com.ldag.se_targetapp.model.CustomImage;
import com.ldag.se_targetapp.model.POI;

public class PlacesScreenFragment extends Fragment {
	
	ViewSwitcher switcher;
	ImageButton Next, Previous;
	private POI[] pois;
	View rootView;   
	private ListView listView;
	private POIListAdapter poiListAdapter;
	GoogleMap map;
	Context context;
	
	public PlacesScreenFragment(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
   	
    	super.onCreate(savedInstanceState);
		
    	// GENERAL UI
        rootView = inflater.inflate(R.layout.fragment_placesscreen, container, false);
    	context = rootView.getContext();
        switcher = (ViewSwitcher) rootView.findViewById(R.id.ViewSwitcher);
        setHasOptionsMenu(true);
		
    	pois = LdagApplication.getSQLHelper().readPois();

        // SWITCH BUTTONS
        Next = (ImageButton) rootView.findViewById(R.id.Next);
        Previous = (ImageButton) rootView.findViewById(R.id.Previous);
        Next.setOnClickListener(new View.OnClickListener() {		
        	public void onClick(View v) {
        		
        		new AnimationUtils();
        		//switcher.setAnimation(AnimationUtils.makeInAnimation(rootView.getContext(), true));
        		switcher.showNext();
        	}
        });
        Previous.setOnClickListener(new View.OnClickListener() {			
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
				
        		new AnimationUtils();
        		//switcher.setAnimation(AnimationUtils.makeInAnimation(rootView.getContext(), true));
        		switcher.showPrevious();
        	}
        });       
        
        // MAP
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(false);
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        addMarkersToMap();
        
        // LIST
        listView = (ListView) rootView.findViewById(android.R.id.list);
        
        return rootView;
    }
    
    @Override
    public void onDestroyView() {
    	// TODO Auto-generated method stub
    	super.onDestroyView();
    	Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
    	FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
    	ft.remove(fragment);
    	ft.commit();
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        poiListAdapter = new POIListAdapter(getActivity(), pois);
        listView.setAdapter(poiListAdapter);
    }
   
   
    private void addMarkersToMap() {
    	
    	for (POI poi : pois) {
       	 LatLng poiLocation = new LatLng (poi.getLatitude(), poi.getLongitude()); 
         map.addMarker(new MarkerOptions().title(poi.getName()).snippet(poi.getStartDate() +" "+ poi.getEndDate()).position(poiLocation));
		}
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_poiscreen_menu, menu);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
       switch (item.getItemId()) {
        case R.id.add_poi:
        	showAddAndEditPoiFragmentView("add");
            return true;
        case R.id.edit_poi:
        	showAddAndEditPoiFragmentView("edit");
            return true;    
        case R.id.remove_poi:
            return true;       
        default:
            return super.onOptionsItemSelected(item);
        }
        
    }
 
    public void showAddAndEditPoiFragmentView(String type) {
        
    	Fragment fragment = new AddAndEditPoiScreenFragment();
        
    	if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
 
        } 
    }
}

