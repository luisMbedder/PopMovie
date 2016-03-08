package com.example.luis.popmovie.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.popmovie.R;
import com.example.luis.popmovie.fragments.MoviePosterFragment;
import com.example.luis.popmovie.network.MovieAPI;
import com.example.luis.popmovie.network.MovieClient;
import com.example.luis.popmovie.utils.MovieGeneral;
import com.example.luis.popmovie.utils.endlessScrollCallback;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements endlessScrollCallback {
    private final String   API_KEY = "1b972f38291bc16e6b2b7468a0014dd8";
    private  final String MOST_POPULAR = "popularity.desc";
    MoviePosterFragment moviePosterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // moviePosterFragment = new MoviePosterFragment();
        moviePosterFragment = (MoviePosterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);


 /*       if(savedInstanceState ==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, moviePosterFragment).commit();

        }*/

       FetchMovies(1);
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      //  MoviePosterFragment fragment = (MoviePosterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_main);
      //  fragment.FetchMovie();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void FetchMovies(int page)
    {

        //create an adapter for retrofit wth base url
        RestAdapter restAdapter = MovieClient.getClient();
        //create adapter to access the methods in MovieAPI interface
        MovieAPI movieAPI = restAdapter.create(MovieAPI.class);
        String PAGE = Integer.toString(page);

        movieAPI.fetchPopMovies(MOST_POPULAR, API_KEY,PAGE,new Callback<MovieGeneral>() {
            @Override
            public void success(MovieGeneral movieGeneral, Response response) {
                //add fragment to layout

              moviePosterFragment.drawLayout(movieGeneral);

                Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(RetrofitError error) {
                int a = 0;
                 Toast.makeText(getApplicationContext(), "fail!",
                          Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void loadMoreMovies(int page){

        int a=0;
        FetchMovies(page);
        /** Do something with the string and return your Integer instead of 0 **/
       // return 0;
    }
}
