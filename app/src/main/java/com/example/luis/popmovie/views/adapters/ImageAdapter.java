package com.example.luis.popmovie.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.luis.popmovie.R;
import com.example.luis.popmovie.models.MovieDBUrl;

import java.net.URI;
import java.net.URL;
import java.util.Objects;

/**
 * Created by Luis on 2/3/2016.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;

    public ImageAdapter(Context c){
        mContext = c;
    }

    public int getCount(){
        return mThumbIds.length;
    }

    //returns the object at the specified position in the adapter
    public Object getItem(int position)
    {
        return null;
    }

    //returns the row id of the item at the specified position in the adapter
    public long getItemId(int position)
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    /*
        position = position in the grid
        convertView: used for recycling Views that are no longer displayed due to scrolling off
                     the screen.
        parent: The parent that this view will eventually be attached to(GridView)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageView class allows you to display an image file
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        MovieDBUrl url = MovieDBUrl.getInstance();
        URL popMoviesHttpUrl = url.getPopularMoviesQuery();

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };


}
