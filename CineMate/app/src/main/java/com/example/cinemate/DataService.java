package com.example.cinemate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {


    @GET("friend/get")
    Call<ArrayList<FriendPojo>> getFriends(@Query(value = "pageNo") String pageNo, @Query(value="pageSize") String pageSize );

    @GET("movie/get")
    Call<ArrayList<Movie>> getMovies();
}
