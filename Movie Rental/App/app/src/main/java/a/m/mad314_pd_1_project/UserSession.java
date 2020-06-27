package a.m.mad314_pd_1_project;

import android.content.SharedPreferences;

class UserSession {

    private UserSession(){}
    private static SharedPreferences userPreferences;
    private static UserSession userSession;

    public static UserSession getInstance(SharedPreferences preferences){
        if(userPreferences == null){
            userPreferences = preferences;
        }

        if(userSession == null)
            userSession = new UserSession();

        return userSession;
    }

    public static UserSession getInstance(){
        if(userSession == null)
            userSession = new UserSession();

        return userSession;
    }

    public  void clearSession(){
        userPreferences.edit().putString("token", "").commit();
        userPreferences.edit().putString("userId", "").commit();
        userPreferences.edit().putString("email", "").commit();
    }

    public void setUserId(String userId){
        userPreferences.edit().putString("userId", userId).commit();
    }

    public void setEmail(String email){
        userPreferences.edit().putString("email", email).commit();
    }

    public void setToken(String token){
        userPreferences.edit().putString("token", token).commit();
    }

    public void setName(String name){
        userPreferences.edit().putString("name", name).commit();
    }

    public String getUserId(){
        return userPreferences.getString("userId", "");
    }

    public String getEmail(){
        return userPreferences.getString("email", "");
    }

    public String getToken(){
        return userPreferences.getString("token", "");
    }

    public String getName(){return userPreferences.getString("name" ,"");}
}
