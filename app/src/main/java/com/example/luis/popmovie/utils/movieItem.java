package com.example.luis.popmovie.utils;

/**
 * Created by Luis on 2/16/2016.
 */
public class movieItem {

    private String mTitle;
    private String mImage;
    private String mBackDropImage;
    private String mOverview;
    private String mVoteAverage;
    final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    final String BACKDROP_URL = "http://image.tmdb.org/t/p/w500/";

    public movieItem(String Title, String Image, String BackdropImage, String Overview, String VoteAverage)
    {

        this.mImage = Image;
        this.mBackDropImage = BackdropImage;
        this.mTitle = Title;
        this.mOverview = Overview;
        this.mVoteAverage = VoteAverage;
    }

    public movieItem(String Image)
    {
        this.mImage=Image;
    }

    public String getImage()
    {
        return IMAGE_URL+mImage;
    }

    public String getBackdropImage(){ return BACKDROP_URL+mBackDropImage; }

    public String getTitle()
    {
        return mTitle;
    }

    public String getOverview()
    {
        return mOverview;
    }

    public String getVoteAverage()
    {
        return mVoteAverage;
    }


}
