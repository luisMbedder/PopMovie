package com.example.luis.popmovie.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luis.popmovie.R;
import com.example.luis.popmovie.activities.MovieDetailActivity;
import com.example.luis.popmovie.network.MovieClient;
import com.example.luis.popmovie.utils.GridClickListener;
import com.example.luis.popmovie.utils.movieItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Luis on 2/3/2016.
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.movieHolder>
{

    private static final String TAG = GridViewAdapter.class.getSimpleName();
    private Context mContext;
    private int layoutResourceId;
    //ArrayList of GridItems
    private ArrayList<movieItem> mGridData = new ArrayList<movieItem>();
    // The minimum amount of items to have below your current scroll position before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    static int holderCount;
//    private  ArrayList<movieHolder> holderArray = new ArrayList<movieHolder>();

    static int gridCellWidth;
    private final GridClickListener mListener;

    public GridViewAdapter(Context passedContext,ArrayList<movieItem> passedGridData,GridClickListener passedListener)
    {
        //super(passedContext,passedLayoutResourceId,passedGridData);
        this.mContext = passedContext;
        //this.layoutResourceId=passedLayoutResourceId;
        this.mGridData=passedGridData;
        mListener = passedListener;

        WindowManager wm = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int windowWidth = display.getWidth();  // deprecated


        int cellWidth = windowWidth / 3;
        gridCellWidth = (cellWidth * 278) / 185;
    }

    //nested inner class for movie holder
    public static class movieHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public TextView mMovieName;
        public ImageView mMoviePoster;
        public View mView;
        public IMyViewHolderClicks mListener;

        public movieHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mMoviePoster = (ImageView)itemView.findViewById(R.id.moviePoster);
            //mMovieName = (TextView)itemView.findViewById(R.id.movieText);
            int h =  mMoviePoster.getMeasuredHeight();
            int w =  mMoviePoster.getMeasuredWidth();

            Log.i(TAG, "h : " + h + " w : " + w);


            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)mMoviePoster.getLayoutParams();

            // subtitle layout params
            LinearLayout.LayoutParams contentSubtitleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, gridCellWidth);
            contentSubtitleParams.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin);
            mMoviePoster.setLayoutParams(contentSubtitleParams);

            Log.i(TAG, "h : " + h + " w : " + w);

            holderCount++;
            Log.i("new movieHolder", String.valueOf(holderCount));
        }

        @Override
        public void onClick(View v) {
           // if (v instanceof ImageView){
                mListener.onTomato((ImageView)v);
          //  } else {
          //      mListener.onPotato(v);
          //  }
        }

        public static interface IMyViewHolderClicks {
         //   public void onPotato(View caller);
            public void onTomato(ImageView callerImage);
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

        //returned to caller(the layout manager)
        Log.i(TAG, "OnCreateViewHolderCalled");
        return movie;
       // return (movieHolder)vh;
    }




    // Replace the contents of a view (invoked by the layout manager)
    //Loads the data at the specified position into the views whose references are stored in the given view holder.
    @Override
    public void onBindViewHolder(final movieHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        try {
            Log.i(TAG, "onBindViewHolder CALLED ... " + mGridData.get(position).getImage());

            Picasso.with(mContext).load(mGridData.get(position).getImage()).into(holder.mMoviePoster);

            holder.mMoviePoster.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    mListener.onGridClicked(holder,position);


                    /**Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    Bundle b = new Bundle();
                    b.putString("backdropImage",mGridData.get(position).getBackdropImage());
                    b.putString("voteAverage", mGridData.get(position).getVoteAverage());
                    b.putString("moviePoster", mGridData.get(position).getImage());
                    intent.putExtra("movieBundle", b);
                    mContext.startActivity(intent); **/
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
 //   }
/*    else
    {
        ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
    }
*/
    }

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
