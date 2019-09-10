package com.ldag.se_targetapp.fragments;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ldag.se_targetapp.LdagApplication;
import com.ldag.se_targetapp.R;
import com.ldag.se_targetapp.model.POI;

	public class AddAndEditPoiScreenFragment extends Fragment implements OnDateSetListener, OnMapClickListener {

		View rootView;
		Uri picture;
		boolean beginningDateIsBeingSet;
		EditText dateBeginning;
		EditText dateEnd;
		EditText poiPlaceName;
		EditText poiDescription;
		double lat;
		double lng;
		
		GoogleMap map;
		public static final String TAG = AddAndEditPoiScreenFragment.class.getName();

		public AddAndEditPoiScreenFragment() {
		}

		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	  
	    	rootView = inflater.inflate(R.layout.fragment_addandeditpoiscreen, container, false);
	                	
	        	OnClickListener l = new OnClickListener() {
	    			@Override
	    		public void onClick(View v) {
	        		int id = v.getId();
//					if (id == R.id.pickCustomImageButton) {
//						System.out.println("dsfsdfdsfsdf");
//						selectPhoto(1);
//					} else 
					if (id == R.id.poidatePickerButtonBeginning) {
						closeKeyBoard(getActivity());
						Calendar c = Calendar.getInstance();
						beginningDateIsBeingSet = true;
						DatePickerDialog d = new DatePickerDialog(getActivity(), AddAndEditPoiScreenFragment.this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
						d.show();
					} if (id == R.id.poidatePickerButtonEnd) {
						closeKeyBoard(getActivity());
						Calendar c = Calendar.getInstance();
						beginningDateIsBeingSet = false;
						DatePickerDialog d = new DatePickerDialog(getActivity(), AddAndEditPoiScreenFragment.this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
						d.show();
					} else if (id == R.id.poiCancelPoiButton) {		
						getActivity().getFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
						
					} else if (id == R.id.poiSavePoiButton) {
						if(poiPlaceName.getText().toString().isEmpty() || poiDescription.getText().toString().isEmpty() || dateBeginning.getText().toString().isEmpty()  || dateEnd.getText().toString().isEmpty() ){
							Toast.makeText(rootView.getContext(),
									"Die Angaben sind unvollstaendig.",
									Toast.LENGTH_LONG).show();
						} else {
			        		POI newPOI = new POI(poiPlaceName.getText().toString(), poiDescription.getText().toString(), dateBeginning.getText().toString(), dateEnd.getText().toString(), lat, lng);
							
							LdagApplication.getSQLHelper().writePoi(newPOI);
							
							getActivity().getFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		
						}
						
					}
	    			}
	    		};
	    		
	    		poiPlaceName = (EditText) rootView.findViewById(R.id.poiplaceName);
	    		poiDescription = (EditText) rootView.findViewById(R.id.poidescription);
	    		
	    		dateBeginning = (EditText) rootView.findViewById(R.id.poidateTextView);
	    		//dateEdit.setOnClickListener(l);
	    		dateBeginning.setOnTouchListener(new OnTouchListener() {
	    			
	    			@Override
	    			public boolean onTouch(View arg0, MotionEvent arg1) {
	    				if(arg1.getAction()!=MotionEvent.ACTION_DOWN){
	    					return true;
	    				}
	    				closeKeyBoard(getActivity());
	    				Calendar c = Calendar.getInstance();
						beginningDateIsBeingSet = true;
	    				DatePickerDialog d = new DatePickerDialog(getActivity(), AddAndEditPoiScreenFragment.this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	    				d.show();
	    				return true;
	    			}
	    		});
	    		dateEnd = (EditText) rootView.findViewById(R.id.poidateTextView2);
	    		//dateEnd.setOnClickListener(l);
	    		dateEnd.setOnTouchListener(new OnTouchListener() {
	    			
	    			@Override
	    			public boolean onTouch(View arg0, MotionEvent arg1) {
	    				if(arg1.getAction()!=MotionEvent.ACTION_DOWN){
	    					return true;
	    				}
	    				closeKeyBoard(getActivity());
						beginningDateIsBeingSet = false;
	    				Calendar c = Calendar.getInstance();
	    				DatePickerDialog d = new DatePickerDialog(getActivity(), AddAndEditPoiScreenFragment.this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	    				d.show();
	    				return true;
	    			}
	    		});

//	    		final ImageButton pickImageButton = (ImageButton) rootView.findViewById(R.id.poipickCustomImageButton);  
//	    		pickImageButton.setOnClickListener(l);
	            // MAP
	            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.poimap)).getMap();
	            map.setMyLocationEnabled(true);
	            map.getUiSettings().setZoomControlsEnabled(false);
	            map.setOnMapClickListener(this); 
	    		final ImageButton pickDateButton = (ImageButton) rootView.findViewById(R.id.poidatePickerButtonBeginning);  
	    		pickDateButton.setOnClickListener(l);
	    		final ImageButton pickDateButtonEnd = (ImageButton) rootView.findViewById(R.id.poidatePickerButtonEnd);  
	    		pickDateButtonEnd.setOnClickListener(l);
	    		final Button savePoiButton = (Button) rootView.findViewById(R.id.poiSavePoiButton);  
	    		savePoiButton.setOnClickListener(l);	
	    		final Button cancelPoiButton = (Button) rootView.findViewById(R.id.poiCancelPoiButton);  
	    		cancelPoiButton.setOnClickListener(l);	
	            
	        return rootView;  
	    }

		public static void closeKeyBoard(Activity context) {
			View view = context.getWindow().getDecorView();
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			System.out.println(dayOfMonth);
			System.out.println(monthOfYear);
			System.out.println(year);
			
			
			if (beginningDateIsBeingSet) {
				EditText editText = (EditText) rootView.findViewById(R.id.poidateTextView);
				monthOfYear++;
				String day = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
				String month = monthOfYear < 10 ? "0" + monthOfYear : "" + monthOfYear;
				editText.setText(day + ". " + month + ". " + year);	
			} else {
				dateEnd = (EditText) rootView.findViewById(R.id.poidateTextView2);
				monthOfYear++;
				String day = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
				String month = monthOfYear < 10 ? "0" + monthOfYear : "" + monthOfYear;
				dateEnd.setText(day + ". " + month + ". " + year);
	
			}
			beginningDateIsBeingSet = false;

		}
		
	    @Override
	    public void onDestroyView() {
	    	// TODO Auto-generated method stub
	    	super.onDestroyView();
	    	Fragment fragment = (getFragmentManager().findFragmentById(R.id.poimap));
	    	FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
	    	ft.remove(fragment);
	    	ft.commit();
	    }

		@Override
		public void onMapClick(LatLng point) {
			lat = point.latitude;
			lng = point.longitude;
			map.clear();
			map.addMarker(new MarkerOptions().position(point));
			Toast.makeText(rootView.getContext(),
					"Neuer Ort: Latidude: " + lat + " / Longitude:" + lng,
					Toast.LENGTH_LONG).show();
			
		}
}
