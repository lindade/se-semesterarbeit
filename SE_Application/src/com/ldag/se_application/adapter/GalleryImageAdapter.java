package com.ldag.se_application.adapter;

import java.util.ArrayList;

import com.ldag.se_application.model.CustomImage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

public class GalleryImageAdapter extends BaseAdapter implements SpinnerAdapter {

	private Context mContext;
	private ArrayList<CustomImage> photos;
	//private PhotoScreenFragment photoScreen;

    public GalleryImageAdapter(Context context, ArrayList<CustomImage> photos)
    {
        this.mContext = context;
        this.photos = photos;
    }

	public int getCount() {
        return this.photos.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        ImageView iView = new ImageView(mContext);

        iView.setImageResource(this.photos.get(index).getImageResourceId());
        iView.setLayoutParams(new Gallery.LayoutParams(156, 104));
   
        iView.setScaleType(ImageView.ScaleType.FIT_XY);

        return iView;
    }
}



