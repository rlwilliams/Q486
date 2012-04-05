package com.qayto.mobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(190, 190));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(15, 15, 15, 15);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
    		R.drawable.finalcomputer, R.drawable.finalcar,
            R.drawable.finalschool, R.drawable.finalpets,
            R.drawable.finalinstruments, R.drawable.finalprogramming,
            R.drawable.finalcooking, R.drawable.finalnature,
            R.drawable.finalmusic, R.drawable.finalsports
    };
    
    
    public String[] categories = {
    	"Computers", "Cars", "Academics", "Pets",
    	"Instruments", "Programming", "Cooking", "Outdoors",
    	"Music", "Sports"
    };
}
