package com.example.luis.popmovie.activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.luis.popmovie.activities.adapters.GridViewAdapter;
import com.example.luis.popmovie.R;
import com.example.luis.popmovie.fragments.MoviePosterFragment;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //returns the intent that started this activity
        Intent intent = getIntent();

        //get selected image position
        int imagePosition = intent.getExtras().getInt(MoviePosterFragment.EXTRA_IMAGE);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this);
        ImageView imageView = (ImageView)findViewById(R.id.SingleView);
        imageView.setImageResource(gridViewAdapter.mThumbIds[imagePosition]);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class MovieDetailFragment extends Fragment
    {
        private static final String LOG_TAG = MoviePosterFragment.class.getSimpleName();

        public MovieDetailFragment()
        {
            setHasOptionsMenu(true);
        }

        public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.fragment_movie_detail,container,false);
            return view;
        }

    }

}
