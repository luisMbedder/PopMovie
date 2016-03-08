package com.example.luis.popmovie.network;

import com.example.luis.popmovie.utils.MovieGeneral;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Luis on 2/24/2016.
 */
public interface MovieAPI
{

@GET("/3/discover/movie")
    void fetchPopMovies
        (
        //@Query: specifies the query key name with the value of the annotated parameter.
        @Query("sort_by") String SORT_PARAM,
        @Query("api_key") String API_KEY,
        @Query("page") String PAGE,
        retrofit.Callback<MovieGeneral> cb //last parameter must be a Callback so API call can run asynchronously
        );

}
