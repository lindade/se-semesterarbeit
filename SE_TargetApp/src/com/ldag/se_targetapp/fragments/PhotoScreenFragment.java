package com.ldag.se_targetapp.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.ldag.se_targetapp.R;
import com.ldag.se_targetapp.adapter.GalleryImageAdapter;
import com.ldag.se_targetapp.model.CustomImage;

public class PhotoScreenFragment extends Fragment {

	public PhotoScreenFragment() {
	}

	ImageView selectedImage;
	ArrayList<CustomImage> photos;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		rootView = inflater.inflate(R.layout.fragment_photoscreen, container,
				false);


		addPhotosToGallery();

		Gallery gallery = (Gallery) rootView.findViewById(R.id.gallery1);
		selectedImage = (ImageView) rootView.findViewById(R.id.imageView1);
		if (gallery == null) {
			System.out.println("gallery ist null");
			System.out.println("Gallery id: " + R.id.gallery1);
		}
		gallery.setSpacing(4);
		gallery.setAdapter(new GalleryImageAdapter(rootView.getContext(),
				photos));

		// clicklistener for Gallery
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				CustomImage photo = photos.get(position);
				Toast.makeText(rootView.getContext(),
						photo.getSubtitle() + " - " + photo.getDateTaken(),
						Toast.LENGTH_LONG).show();
				// show the selected Image
				selectedImage.setImageResource(photos.get(position)
						.getImageResourceId());
			}
		});
		return rootView;
	}
	
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_photoscreen_menu, menu);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
       switch (item.getItemId()) {
        case R.id.add_photo:
        	showAddAndEditPhotoFragmentView("add");
            return true;
        case R.id.edit_photo:
        	showAddAndEditPhotoFragmentView("edit");
            return true;    
        case R.id.remove_photo:
            return true;       
        default:
            return super.onOptionsItemSelected(item);
        }
        
    }
 
    public void showAddAndEditPhotoFragmentView(String type) {
    
    	Fragment fragment = new AddAndEditPhotoScreenFragment();
        
    	if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
 
        } 
    }
    
	public void addPhotosToGallery() {

		Context context = rootView.getContext();

		photos = new ArrayList<CustomImage>();
		photos.add(new CustomImage("gallery_sydney", "Sydney, Australien",
				"06.01.2013", context));
		photos.add(new CustomImage("gallery_cornwallruins",
				"Cornwall, Grossbritannien", "22.02.2013", context));
		photos.add(new CustomImage("gallery_lakedistrict",
				"Lake District, Grossbritannien", "17.03.2013", context));
		photos.add(new CustomImage("gallery_mexico", "Palenque, Mexico",
				"12.04.2013", context));
		photos.add(new CustomImage("gallery_monumentvalley",
				"Monument Valley, USA", "22.05.2013", context));
		photos.add(new CustomImage("gallery_nz", "Mackenzie Basin, Neuseeland",
				"18.06.2013", context));
		photos.add(new CustomImage("gallery_oz",
				"Cape Tribulation, Australien", "22.07.2013", context));
		photos.add(new CustomImage("gallery_yosemitevallay",
				"Yosemite Valley, USA", "08.12.2013", context));
		photos.add(new CustomImage("gallery_petra", "Petra, Jordanien",
				"29.08.2013", context));
		photos.add(new CustomImage("gallery_tajmahal", "Taj Mahal, Indien",
				"02.09.2013", context));
		photos.add(new CustomImage("gallery_torreschile",
				"Torres del Paine, Chile", "22.10.2013", context));
		photos.add(new CustomImage("gallery_venedig", "Venedig, Italien",
				"31.11.2013", context));
	}

}
