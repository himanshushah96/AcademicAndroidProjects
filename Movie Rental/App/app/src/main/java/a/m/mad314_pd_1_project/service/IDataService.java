package a.m.mad314_pd_1_project.service;

import java.util.ArrayList;

import a.m.mad314_pd_1_project.requestmodel.RentRequestModel;
import a.m.mad314_pd_1_project.responsemodel.LoginResponseModel;
import a.m.mad314_pd_1_project.responsemodel.MovieResponse;
import a.m.mad314_pd_1_project.responsemodel.RentedListResponse;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IDataService {


    @GET("movies")
    Call<ResponseModel<ArrayList<MovieResponse>>> getMovies();

    @POST("rentMovie")
    Call<ResponseModel<String>> movieRent(@Body RentRequestModel rentRequestModel);


    @GET("rentedMovieList")
    Call<ResponseModel<ArrayList<RentedListResponse>>> getRentedMovies(@Query(value = "userId") String userId);


}
