package com.example.luis.popmovie.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.luis.popmovie.views.adapters.ImageAdapter;
import com.example.luis.popmovie.views.MovieDetailActivity;
import com.example.luis.popmovie.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviePosterFragment extends Fragment {

    GridView movieGrid;
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
        //set ImageAdapter as the source for all times to be displayed on the grid
        movieGrid.setAdapter(new ImageAdapter(getActivity()));

        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {
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



        return rootView;

    }
}
