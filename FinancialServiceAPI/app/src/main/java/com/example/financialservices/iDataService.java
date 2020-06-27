package com.example.financialservices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface iDataService {


    @GET("stock/gainers")
    Call<MostGainer> getMostGainer ();

    @GET("stock/losers")
    Call<MostLoser> getMostLoser ();

    @GET("is-the-market-open")
    Call<TradingHours> getTradingHours ();

    @GET("company/profile/{profile}")
    Call<CompanyProfile> getCompanyProfile(@Path(value = "profile", encoded = true) String profile);

}


