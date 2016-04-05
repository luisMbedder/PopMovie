package com.example.luis.popmovie.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luis.popmovie.R;
import com.example.luis.popmovie.utils.MovieItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Luis on 3/20/2016.
 */
public class MovieDetailFragment extends Fragment {

        private static final String LOG_TAG = MoviePosterFragment.class.getSimpleName();
        private int posterH;
        private int posterW;
        private String movieTitle;

    public MovieDetailFragment()
        {

        }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        DisplayMetrics metrics = this.getActivity().getResources().getDisplayMetrics();
        int windowWidth = metrics.widthPixels;
        posterW = (int)(windowWidth*.27);
        posterH = (posterW*278)/185;
    }


      @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_movie_detail,container,false);
            //returns the intent that started this activity
            Intent intent = getActivity().getIntent();
            if(intent!=null && intent.hasExtra("movieParcel"))
            {
                MovieItem movie = intent.getParcelableExtra("movieParcel");
                movieTitle = movie.getTitle();
                String backdropImage = movie.getBackdropImage();
                Float voteAverage = movie.getVoteAverage();
                int voteCount = movie.getVoteCount();
                String overview = movie.getOverview();
                String releaseDate = movie.getReleaseDate();

                final CollapsingToolbarLayout collapsingToolbarLayout =
                        (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
                AppBarLayout appbar = (AppBarLayout) rootView.findViewById(R.id.appbar);
                appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

                    boolean isShow = false;
                    int scrollRange = -1;

                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
                    {
                        if (scrollRange == -1)
                        {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0 ||(scrollRange + verticalOffset)<0)
                        {
                            collapsingToolbarLayout.setTitle(movieTitle);
                            isShow = true;
                        }
                        else if (isShow)
                        {
                            collapsingToolbarLayout.setTitle("");
                            isShow = false;
                        }
                    }

                });
                Bitmap bitmap = MoviePosterFragment.photoCache.get(1);
                int h =bitmap.getHeight();
                int w  = bitmap.getWidth();
                ImageView backdropIV = (ImageView)rootView.findViewById(R.id.backdrop);
                ImageView iv2 = (ImageView)rootView.findViewById(R.id.moviePoster);
                int backdropWidth = backdropIV.getWidth();
                int backdropHeight = backdropIV.getHeight();

                DisplayMetrics metrics = this.getActivity().getResources().getDisplayMetrics();
                int windowHeight = metrics.heightPixels;
                int backdropH = (int)(windowHeight *.333);

                iv2.getLayoutParams().height = bitmap.getHeight();
                iv2.getLayoutParams().width = bitmap.getWidth();
              // Toolbar.LayoutParams backdropLP = new Toolbar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,backdropH);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(posterW,posterH);
                lp.leftMargin = 42;
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)iv2.getLayoutParams();
             //   lp.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin);
                iv2.setLayoutParams(lp);
             //   backdropIV.setLayoutParams(backdropLP);
                int h2 = iv2.getHeight();
                int w2 =iv2.getWidth();
                iv2.setImageBitmap(bitmap);
                iv2.setAdjustViewBounds(true);
                 h2 = iv2.getHeight();
                 w2 =iv2.getWidth();
                Picasso.with(getActivity()).load(backdropImage).placeholder(R.drawable.backdrop_placeholder).into(backdropIV);
             //   Glide.with(getActivity()).load(backdropImage).placeholder(R.drawable.backdrop_placeholder2).into(backdropIV);
            }
            return rootView;
        }



}
