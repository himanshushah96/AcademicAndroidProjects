package a.m.mad314_pd_1_project;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String Base_url = "http://192.168.0.173/api/";

    static UserSession session;
    static String token;

    public static Retrofit getRetrofitInstance(){

        session = UserSession.getInstance();
        token = session.getToken();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        if (token != null && !token.equals("")) {
                            ongoing.addHeader("Authorization", "Bearer "+token);
                        }
                        return chain.proceed(ongoing.build());
                    }
                }).build();

        if (retrofit==null){

            retrofit =new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;

    }
}
