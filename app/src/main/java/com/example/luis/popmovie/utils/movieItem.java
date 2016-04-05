package com.example.luis.popmovie.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luis on 2/16/2016.
 */
public class MovieItem implements Parcelable{

    private String mTitle;
    private String mPosterImage;
    private String mBackDropImage;
    private String mOverview;
    private float mVoteAverage;
    private int mVoteCount;
    private int mMovieId;
    private String mReleaseDate;
    final String IMAGE_URL = "http://image.tmdb.org/t/p/w342/";
    final String BACKDROP_URL = "http://image.tmdb.org/t/p/w780/";

    public MovieItem(String Title, String Image, String BackdropImage, String Overview, Float VoteAverage, int VoteCount, String ReleaseDate, int MovieId)
    {
        this.mBackDropImage = BackdropImage;
        this.mPosterImage = Image;
        this.mTitle = Title;
        this.mOverview = Overview;
        this.mVoteAverage = VoteAverage;
        this.mVoteCount = VoteCount;
        this.mReleaseDate = ReleaseDate;
        this.mMovieId = MovieId;
    }

    public MovieItem(Parcel in)
    {
        this.mBackDropImage = in.readString();
        this.mPosterImage = in.readString();
        this.mTitle = in.readString();
        this.mOverview = in.readString();
        this.mVoteAverage = in.readFloat();
        this.mVoteCount = in.readInt();
        this.mReleaseDate = in.readString();
        this.mMovieId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>()
    {
        public MovieItem createFromParcel(Parcel source)
        {
            return new MovieItem(source);
        }

        public MovieItem[] newArray(int size)
        {
            return new MovieItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(mBackDropImage);
        dest.writeString(mPosterImage);
     //   dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mOverview);
       // dest.writeLong(releaseDate.getTime());
        dest.writeFloat(mVoteAverage);
        dest.writeInt(mVoteCount);
        dest.writeString(mReleaseDate);
        dest.writeInt(mMovieId);
    }

    public MovieItem(String Image)
    {
        this.mPosterImage=Image;
    }

    public String getPosterImage()
    {
        return IMAGE_URL+mPosterImage;
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

    public float getVoteAverage()
    {
        return mVoteAverage;
    }

    public int getVoteCount()
    {
        return mVoteCount;
    }


    public String getReleaseDate()
    {
        return mReleaseDate;
    }

    public int getMovieId()
    {
        return mMovieId;
    }


}
