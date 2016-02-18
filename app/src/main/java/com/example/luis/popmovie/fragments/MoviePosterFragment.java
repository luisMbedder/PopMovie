package com.example.luis.popmovie.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.luis.popmovie.activities.adapters.GridViewAdapter;
import com.example.luis.popmovie.utils.AsyncDownloader;
import com.example.luis.popmovie.utils.GridItem;
import com.example.luis.popmovie.utils.MovieDBUrl;
import com.example.luis.popmovie.activities.MovieDetailActivity;
import com.example.luis.popmovie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviePosterFragment extends Fragment {

    private final String LOG_TAG = MoviePosterFragment.class.getSimpleName();
    private GridViewAdapter mGridAdapter;
    private GridView movieGrid;
    private ArrayList<GridItem> mGridData;
    //key for thumbnail image position
    public final static String EXTRA_IMAGE = "com.example.luis.sunshine.app.IMAGE";
    public MoviePosterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate fragment object
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //get gridView
        movieGrid = (GridView)rootView.findViewById(R.id.movieGridView);
        mGridData = new ArrayList<GridItem>();
        //set GridViewAdapter as the source for all times to be displayed on the grid
        mGridAdapter = new GridViewAdapter(getActivity(),R.layout.grid_item_layout,mGridData);



        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get activity the fragment is associated with
                Context context = getActivity();
                //parent:The adapterview where the click happened
                //Object textObject = parent.getItemAtPosition(position);
                //String forecast = textObject.toString();
                //starting a new activity is packaged as an intent
                Intent intent = new Intent(context, MovieDetailActivity.class);


                intent.putExtra(EXTRA_IMAGE, position);
                //start detail Activity
                startActivity(intent);
            }

        });


        updateMoviePosters();

        return rootView;

    }

    private void updateMoviePosters()
    {
        MovieDBUrl url = MovieDBUrl.getInstance();
        URL popMoviesUrl = url.getPopularMoviesQuery();

        AsyncDownloader downloader = new AsyncDownloader(this);
        downloader.execute(popMoviesUrl);
    }

    public void parseJson(String result)
    {
        //String LOG_TAG = "parseJson";
        //These are the names of the JSON object that need to be extracted
        final String TMDB_RESULTS = "results";
        final String TMDB_POSTER = "poster_path";
        final String TMDB_POPULARITY = "popularity";
        final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
        String poster;
        try
        {
            JSONObject response = new JSONObject(result);
            JSONArray moviesArray = response.getJSONArray(TMDB_RESULTS);
            GridItem item=null;
            for(int i = 0;i < moviesArray.length();i++)
            {
                JSONObject movie = moviesArray.getJSONObject(i);
                poster = IMAGE_URL+movie.getString(TMDB_POSTER);
                item = new GridItem();
                item.setImage(poster);
                mGridData.add(item);
            }

            mGridAdapter.setGridData(mGridData);
            movieGrid.setAdapter(mGridAdapter);

        }
        catch (JSONException e)
        {
            Log.e(LOG_TAG, "Error creating JSONObject in parseJson()", e);
        }

    }


    @Override
    public void onStart()
    {
        super.onStart();
     //   updateMoviePosters();
    }

}
