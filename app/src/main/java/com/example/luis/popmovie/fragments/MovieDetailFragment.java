package com.example.luis.popmovie.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luis.popmovie.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Luis on 3/20/2016.
 */
public class MovieDetailFragment extends Fragment {

        private static final String LOG_TAG = MoviePosterFragment.class.getSimpleName();

        public MovieDetailFragment()
        {
            setHasOptionsMenu(true);
        }

        public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_movie_detail,container,false);
            //returns the intent that started this activity
            Intent intent = getActivity().getIntent();
            if(intent!=null && intent.hasExtra("movieBundle"))
            {
                Bundle b = intent.getBundleExtra("movieBundle");
                String backdropImage = b.getString("backdropImage");
                String poster = b.getString("moviePoster");
                String voteAverage = b.getString("voteAverage");
                CollapsingToolbarLayout collapsingToolbar =
                        (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
                collapsingToolbar.setTitle("movie Name");
                ImageView iv = (ImageView)rootView.findViewById(R.id.backdrop);
             //   ImageView iv2 = (ImageView)rootView.findViewById(R.id.moviePoster);
                Picasso.with(getActivity()).load(backdropImage).into(iv);
             //   Picasso.with(getActivity()).load(poster).into(iv2);
            }


            return rootView;
        }
}
