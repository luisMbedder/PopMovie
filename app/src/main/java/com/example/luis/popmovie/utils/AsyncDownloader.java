package com.example.luis.popmovie.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.luis.popmovie.fragments.MoviePosterFragment;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Luis on 2/9/2016.
 */
public class AsyncDownloader extends AsyncTask<URL,Void,String> {

    MoviePosterFragment container;
    public AsyncDownloader(MoviePosterFragment passedFragment)
    {
            this.container = passedFragment;
    }

    //this will get passed the moveDB url
    protected String doInBackground(URL... params)
    {
        URL popMovieUrl = params[0];

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(popMovieUrl).build();

        Call call = client.newCall(request);

        Response response = null;

        String jsonData = null;

        try{
            response = call.execute();

            if(response.isSuccessful())
            {

                jsonData = response.body().string();

            }
            else
            {
                jsonData = null;
            }
        }
        catch (IOException e)
        {
            Log.e("AsyncDownloader","Error",e);
            return null;
        }

        return jsonData;
    }

    @Override
    protected void onPostExecute(String result)
    {
        // The activity can be null if it is thrown out by Android while task is running!
        if(container!=null && container.getActivity()!=null)
        {
            container.parseJson(result);
            this.container=null;
        }
        //***test else case***

    }


}
