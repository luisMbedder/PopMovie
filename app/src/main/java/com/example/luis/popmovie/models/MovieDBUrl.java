package com.example.luis.popmovie.models;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Luis on 2/9/2016.
 * Singleton class to store the MovieDBUrl for theMovieDB API
 */
public class MovieDBUrl {

    //Volatile keyword ensures that multiple threads handle the unique/instance correctly
    private volatile static MovieDBUrl uniqueInstance;

    private final String POP_MOVIES_URL = "http://api.themoviedb.org/3/discover/movie";
    private final String IMAGE_URL = "http://image.tmdb.org/t/p/";
    private final String API_KEY = "1b972f38291bc16e6b2b7468a0014dd8";

    final String SORT_PARAM = "sort_by=";
    final String KEY ="api_key=";

    private MovieDBUrl()
    {

    }

    //Check for an instance and if there isn't one enter the synchronized method
    public static MovieDBUrl getInstance() {
        if (uniqueInstance == null) {
            synchronized (MovieDBUrl.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new MovieDBUrl(); //Once in the block, check again and if still null, create the instance
                }
            }
        }
        return uniqueInstance;
    }

    public URL getPopularMoviesQuery()
    {
        String TAG = "getPopularMoviesQuery";
        URL parsedURL = null;

        try {
            Uri builtUri = Uri.parse(POP_MOVIES_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, "popularity.desc&")
                    .appendQueryParameter(KEY, API_KEY).build();
            parsedURL = new URL(builtUri.toString());
        }
        catch(MalformedURLException e){
            Log.e(TAG ,"Malformed URLException caught : " + e);
            return null;
        }

        return parsedURL;

    }

    public String getFilmographyQuery(int actorId)
    {
        return POP_MOVIES_URL + "person/" + actorId + "?api_key=" + API_KEY + "&append_to_response=credits";
    }




}
