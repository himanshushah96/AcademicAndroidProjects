package a.m.mad314_pd_1_project.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("userid")
    @Expose
    private String userid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    @Expose
    private String name;

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        userid = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
