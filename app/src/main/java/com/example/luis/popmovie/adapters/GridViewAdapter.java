package com.example.luis.popmovie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luis.popmovie.R;
import com.example.luis.popmovie.utils.movieItem;

/**
 * Created by Luis on 2/3/2016.
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.movieHolder>
{

    private Context mContext;
    private int layoutResourceId;
    //ArrayList of GridItems
    private ArrayList<movieItem> mGridData = new ArrayList<movieItem>();
    // The minimum amount of items to have below your current scroll position before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    static int holderCount;

//    private OnLoadMoreListener onLoadMoreListener;

    public GridViewAdapter(Context passedContext,ArrayList<movieItem> passedGridData,RecyclerView passedRecyclerView)
    {
        //super(passedContext,passedLayoutResourceId,passedGridData);
        this.mContext = passedContext;
        //this.layoutResourceId=passedLayoutResourceId;
        this.mGridData=passedGridData;
    }

    //nested inner class for movie holder
    public static class movieHolder extends RecyclerView.ViewHolder
    {

        public TextView mMovieName;
        public ImageView mMoviePoster;
        public View mView;

        public movieHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mMoviePoster = (ImageView)itemView.findViewById(R.id.moviePoster);
            mMovieName = (TextView)itemView.findViewById(R.id.movieText);

            holderCount++;
            Log.i("new movieHolder", String.valueOf(holderCount));
        }
    }

/*    @Override
    public int getItemViewType(int position)
    {
        return mGridData.get(position) !=null ? VIEW_ITEM : VIEW_PROGRESS_BAR;
    }

*/
    // Create new views (invoked by the layout manager when the recyclerView needs
    //a new view holder to represent an item.
    //Instantiates the item layout file and view holder
    @Override
    public movieHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh;
      //  if(viewType == VIEW_ITEM) {
            // inflate the item view from the view's layout file
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.grid_item_layout, parent, false);
            //wrap the view in a new movie object
            movieHolder movie = new movieHolder(v);
          //  vh = new movieHolder(v);
     /*   }
        else
        {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        } */
        //returned to caller(the layout manager)
        return movie;
       // return (movieHolder)vh;
    }




    // Replace the contents of a view (invoked by the layout manager)
    //Loads the data at the specified position into the views whose references are stored in the given view holder.
    @Override
    public void onBindViewHolder(movieHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

//    if(holder instanceof movieHolder) {
        // Picasso.with(mContext).load(mGridData.get(position).getImage()).into(holder.mMoviePoster);
        //Glide proves to be faster than picasso for image loading.
        Glide.with(mContext).load(mGridData.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mMoviePoster);
        int a = 0;
 //   }
/*    else
    {
        ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
    }
*/
    }

 /*   public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
*/

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
       int a = mGridData.size();
        return mGridData.size();
    }

    //returns the object at the specified position in the adapter
    /*public Object getItem(int position)
    {
        return null;
    }*/

    public void setGridData(ArrayList<movieItem> passedGridData)
    {
        this.mGridData = passedGridData;
     //   notifyDataSetChanged();
    }

    //returns the row id of the item at the specified position in the adapter
    public long getItemId(int position)
    {

        return 0;
    }

    static class ViewHolder
    {
        ImageView imageView;
    }

}
