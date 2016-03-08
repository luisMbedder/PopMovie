package com.example.luis.popmovie.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Luis on 2/26/2016.
 *
 * Singleton class for Retrofit RestAdapter and API (Retrofit 1.9.0)
 */


public class MovieClient {
    private final String   API_KEY = "1b972f38291bc16e6b2b7468a0014dd8";
    private static String BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
    private  final String MOST_POPULAR = "popularity.desc";
    private static RestAdapter mMovieClient;
    private static RestAdapter mRestAdapter;

    private MovieClient()
    {

    }
    public static  RestAdapter getClient()
    {
        if(mMovieClient==null)
        {
            synchronized (MovieClient.class)
            {
                if(mMovieClient==null) {
                    mMovieClient = new
                            RestAdapter.Builder().setEndpoint(BASE_URL)
                            .setClient(new OkClient(new OkHttpClient()))
                            .build();
                }
            }
        }
        return mMovieClient;
    }
}

