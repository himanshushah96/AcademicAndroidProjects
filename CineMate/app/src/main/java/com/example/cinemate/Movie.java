package com.example.cinemate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("movieId")
    @Expose
    private Integer movieId;
    @SerializedName("movieName")
    @Expose
    private String movieName;
    @SerializedName("releaseYear")
    @Expose
    private Double releaseYear;
    @SerializedName("movieDesc")
    @Expose
    private String movieDesc;
    @SerializedName("ratings")
    @Expose
    private Double ratings;
    @SerializedName("movieImage")
    @Expose
    private String movieImage;
    @SerializedName("runTime")
    @Expose
    private String runTime;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Double releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getMovieImage() {
        return movieImage;
    }

    //public void setMovieImage(String movieImage) {
    //  this.movieImage = movieImage;
    //}

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

}