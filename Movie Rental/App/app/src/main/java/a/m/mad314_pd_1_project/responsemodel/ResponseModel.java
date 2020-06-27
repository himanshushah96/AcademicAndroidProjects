package a.m.mad314_pd_1_project.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel<T> {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private T data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResponse() {
        return data;
    }

    public void setResponse(T response) {
        data = response;
    }
}
