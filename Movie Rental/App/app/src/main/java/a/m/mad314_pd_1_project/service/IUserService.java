package a.m.mad314_pd_1_project.service;

import a.m.mad314_pd_1_project.requestmodel.LoginRequestModel;
import a.m.mad314_pd_1_project.requestmodel.RegisterRequestModel;
import a.m.mad314_pd_1_project.responsemodel.LoginResponseModel;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IUserService {

    @POST("login")
    Call<ResponseModel<LoginResponseModel>> login(@Body LoginRequestModel loginRequestModel);

    @POST("register")
    Call<ResponseModel<String>> register (@Body RegisterRequestModel registerRequestModel);
}
