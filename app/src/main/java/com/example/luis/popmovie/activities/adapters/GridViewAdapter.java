package com.example.luis.popmovie.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.luis.popmovie.R;
import com.example.luis.popmovie.utils.GridItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Luis on 2/3/2016.
 */
public class GridViewAdapter extends ArrayAdapter<GridItem>{
    private Context mContext;
    private int layoutResourceId;
    //ArrayList of GridItems
    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();


    public GridViewAdapter(Context passedContext,int passedLayoutResourceId,ArrayList<GridItem> passedGridData)
    {
        super(passedContext,passedLayoutResourceId,passedGridData);
        this.mContext = passedContext;
        this.layoutResourceId=passedLayoutResourceId;
        this.mGridData=passedGridData;
    }

    public int getCount(){
        return mThumbIds.length;
    }

    //returns the object at the specified position in the adapter
    /*public Object getItem(int position)
    {
        return null;
    }*/

    public void setGridData(ArrayList<GridItem> passedGridData)
    {
        this.mGridData = passedGridData;
        notifyDataSetChanged();
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
        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
            row.setTag(holder);
           // imageView = new ImageView(mContext);
           // imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
          //  imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
          //  imageView.setPadding(8, 8, 8, 8);
        } else {
            holder = (ViewHolder) row.getTag();
            //imageView = (ImageView) convertView;
        }

        GridItem item = mGridData.get(position);
        Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        //imageView.setImageResource(mThumbIds[position]);
        return row;
    }

    static class ViewHolder
    {
        ImageView imageView;
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
