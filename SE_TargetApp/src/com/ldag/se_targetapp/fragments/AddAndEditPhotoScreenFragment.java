package com.ldag.se_targetapp.fragments;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import com.ldag.se_targetapp.R;

public class AddAndEditPhotoScreenFragment extends Fragment implements
		OnDateSetListener {

	private static Map<String,Object> cacheData = new HashMap<String,Object>();
	View rootView;
	Uri picture;
	EditText dateEdit;
	EditText descriptionEdit;
	EditText placeNameEdit;
	
	public static final String TAG = AddAndEditPhotoScreenFragment.class.getName();

	public AddAndEditPhotoScreenFragment() {
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
    	rootView = inflater.inflate(R.layout.fragment_addandeditphotoscreen, container, false);
                	
        	OnClickListener l = new OnClickListener() {
    			@Override
    		public void onClick(View v) {
        		int id = v.getId();
				if (id == R.id.pickCustomImageButton) {
					selectPhoto(1);
				} else if (id == R.id.datePickerButton) {
					closeKeyBoard(getActivity());
					Calendar c = Calendar.getInstance();
					DatePickerDialog d = new DatePickerDialog(getActivity(), AddAndEditPhotoScreenFragment.this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
					d.show();
				} else if (id == R.id.saveImageButton) {
	        		getActivity().getFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}
    			}
    		};
    		
    		descriptionEdit = (EditText) rootView.findViewById(R.id.description);
    		placeNameEdit = (EditText) rootView.findViewById(R.id.placeName);
    		dateEdit = (EditText) rootView.findViewById(R.id.dateTextView);
    		//dateEdit.setOnClickListener(l);
    		dateEdit.setOnTouchListener(new OnTouchListener() {
    			
    			@Override
    			public boolean onTouch(View arg0, MotionEvent arg1) {
    				if(arg1.getAction()!=MotionEvent.ACTION_DOWN){
    					return true;
    				}
    				closeKeyBoard(getActivity());
    				Calendar c = Calendar.getInstance();
    				DatePickerDialog d = new DatePickerDialog(getActivity(), AddAndEditPhotoScreenFragment.this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    				d.show();
    				return true;
    			}
    		});
    		

    		final ImageButton pickImageButton = (ImageButton) rootView.findViewById(R.id.pickCustomImageButton);  
    		pickImageButton.setOnClickListener(l);	
    		final ImageButton pickDateButton = (ImageButton) rootView.findViewById(R.id.datePickerButton);  
    		pickDateButton.setOnClickListener(l);	
    		final Button saveImageButton = (Button) rootView.findViewById(R.id.saveImageButton);  
            saveImageButton.setOnClickListener(l);	
            
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
		dateEdit = (EditText) rootView.findViewById(R.id.dateTextView);
		monthOfYear++;
		String day = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
		String month = monthOfYear < 10 ? "0" + monthOfYear : "" + monthOfYear;
		dateEdit.setText(day + ". " + month + ". " + year);
	}
	
	public void selectPhoto(final int res) {

		final Dialog selectDialog = new Dialog(getActivity());
		selectDialog.setContentView(R.layout.dialog_select_picture);
		selectDialog.setTitle("Auswahloptionen");

		OnClickListener l = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int selected = res;
				if (v.getId() == R.id.dialog_select_picture_2) {
					selected += 10;
				}
				if (selected < 10) {
					Intent intent = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					picture = null;
					startActivityForResult(intent, selected);
				} else {
					Intent intent = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					File f = new File(getCachePath(getActivity()),"/"+System.currentTimeMillis()+".jpg");
					picture = Uri.fromFile(f);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, picture);
					startActivityForResult(intent, selected);
				}
				selectDialog.dismiss();
			}
		};

		Button b1 = (Button) selectDialog
				.findViewById(R.id.dialog_select_picture_1);
		b1.setOnClickListener(l);
		Button b2 = (Button) selectDialog
				.findViewById(R.id.dialog_select_picture_2);
		b2.setOnClickListener(l);
		selectDialog.show();
	}


	public static File getCachePath(Context context){
		File cacheDir;
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
			cacheDir = new File(android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/com.ldag/");
			cacheDir.mkdirs();
		}else{
			cacheDir = context.getCacheDir();
		}
		return cacheDir;
	}
	
	public static void setCacheData(String key, Object value){
		cacheData.put(key, value);
	}
	
	public static Object getCacheData(String key){
		return cacheData.get(key);
	}
	
	 public static Bitmap decodeFile(InputStream in,int maxsize){
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len;
	            while ((len = in.read(buffer)) > -1 ) {
	               baos.write(buffer, 0, len);
	            }
	            baos.flush();
	            InputStream is1 = new ByteArrayInputStream(baos.toByteArray()); 
	            InputStream is2 = new ByteArrayInputStream(baos.toByteArray()); 

	            BitmapFactory.Options o = new BitmapFactory.Options();
	            o.inJustDecodeBounds = true;
	            BitmapFactory.decodeStream(is1,null,o);

	            final int IMAGE_MAX_SIZE=maxsize;

	            System.out.println("h:" + o.outHeight + " w:" + o.outWidth);
	            int scale = 1;
	            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
	                scale = (int)Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / 
	                   (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
	            }

	            BitmapFactory.Options o2 = new BitmapFactory.Options();
	            o2.inSampleSize=scale;
	            return BitmapFactory.decodeStream(is2, null, o2);
	        } catch (Exception e) {
	            return null;
	        }
	    }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null && picture == null) {
			return;
		}
		Bitmap thumb = null;
		Uri selectedImage=null;
		if(picture == null){
			selectedImage = data.getData();
		}else{
			selectedImage = picture;
		}

		requestCode %= 10;
		if (selectedImage != null) {
			ImageButton button = null;
				button = (ImageButton) rootView
						.findViewById(R.id.pickCustomImageButton);
			if (button != null) {
				try {
					InputStream stream = getActivity().getContentResolver().openInputStream(
	                        selectedImage);
					thumb = decodeFile(stream,button.getWidth());
	                stream.close();
				}catch(IOException io){
					return;
				}catch (Exception e) {
					e.printStackTrace();
					return;
				}
				button.setImageBitmap(thumb);
				setCacheData(TAG + " " + requestCode, thumb);
			}
		}
	}
}
