package com.example.luis.popmovie.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.luis.popmovie.adapters.GridViewAdapter;
import com.example.luis.popmovie.utils.MovieGeneral;
import com.example.luis.popmovie.R;
import com.example.luis.popmovie.utils.Results;
import com.example.luis.popmovie.utils.endlessScrollCallback;
import com.example.luis.popmovie.utils.movieItem;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviePosterFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter GridAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private ArrayList<movieItem> movieItemArray = new ArrayList<movieItem>();
    private int visibleThreshold = 5;//download more images when there are 5 images left
    private int NUM_COLUMNS = 3;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private endlessScrollCallback callback;
    private int currentPage = 2;//update movies starting at page 2
    private ProgressWheel mProgressWheel;


    public MoviePosterFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mlayoutManager = new GridLayoutManager(getActivity(),NUM_COLUMNS);


       mRecyclerView = (RecyclerView)rootView.findViewById(R.id.pop_movies_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mlayoutManager);
        //set adapter with empty data
        GridAdapter = new GridViewAdapter(getContext(),movieItemArray,mRecyclerView);
        mRecyclerView.setAdapter(GridAdapter);
      //  mProgressWheel = (ProgressWheel) rootView.findViewById(android.R.id.progress);
       endlessScroll();
        Activity act =  getActivity();
        int a =0;
   //  rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        if (container == null) {
            Log.i("in movieposterfragment", "onCreateView(): container = null");
        }
        else
        {
            Log.i("in movieposterfragment", "onCreateView(): container != null");
        }
        return rootView;


    }

        public void drawLayout(MovieGeneral movieGeneral)
        {

            Results[] mResults = movieGeneral.getResults();

         /*  if(movieItemArray.size()!=0) {
                int a = movieItemArray.size() - 1;
                movieItemArray.remove(movieItemArray.size() - 1);

                GridAdapter.notifyItemRemoved(movieItemArray.size());
            }*/

            if(mResults.length>0)
            {
                for (Results result : mResults) {
                    movieItem movieObject = new movieItem(result.getTitle(), result.getPoster_path()
                            , result.getOverview(), result.getVote_average());
                 //  movieItemArray.add(movieObject);
                    addItem(movieObject);

                }
              //  GridAdapter = new GridViewAdapter(getContext(),movieItemArray);
              //  mRecyclerView.setAdapter(GridAdapter);
          //     GridAdapter.notifyDataSetChanged();
                setLoaded();

            }

         //   for()
        //    GridAdapter = new GridViewAdapter();
        //    rv.setAdapter(GridAdapter);


        }

    public void addItem(movieItem item) {
        if (!movieItemArray.contains(item)) {
            movieItemArray.add(item);
            int a  =movieItemArray.size() - 1;
            Log.i("movieItemArray", String.valueOf(movieItemArray.size()));
            GridAdapter.notifyItemInserted(movieItemArray.size()-1);//change back to - 1

        }
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (endlessScrollCallback) context;


    }


    private void endlessScroll() {

        if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager mGridLayoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
            int a = mGridLayoutManager.getSpanCount();

             visibleThreshold=visibleThreshold* mGridLayoutManager.getSpanCount();

            //   visibleThreshold =1;
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisibleItemPositions = mGridLayoutManager.findLastVisibleItemPosition();
                    totalItemCount = mGridLayoutManager.getItemCount();
                    int h = mGridLayoutManager.getHeight();
                    lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        callback.loadMoreMovies(currentPage);
                        currentPage++;
                     //   mProgressWheel.spin();
                       loading = true;
                    }
                }

            });
        }
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions)
    {
        int maxSize =0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    public void setLoaded()
    {

        loading=false;
        //mProgressWheel.stopSpinning();

    }

}
