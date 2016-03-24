package com.example.luis.popmovie.activities;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.luis.popmovie.R;
import com.example.luis.popmovie.fragments.MoviePosterFragment;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        //ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // GridViewAdapter gridViewAdapter = new GridViewAdapter(this,R.layout.movie_detail_layout,);
      //  ImageView imageView = (ImageView)findViewById(R.id.SingleView);
       // imageView.setImageResource(gridViewAdapter.mThumbIds[imagePosition]);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
