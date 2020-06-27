package a.m.carrental.model;

public class Session {

    private String email;
    private String userType;
    private String userName;

    private static Session instance;

    private Session(){

    }

    public static Session getInstance(){
        if(Session.instance == null){
            instance = new Session();
        }
        return instance;
    }

    public void setUserEmail(String email){
        instance.email = email;
    }

    public String getUserEmail(){
        return instance.email;
    }

    public void setUserType(String userType){
        instance.userType = userType;
    }

    public void setUserName(String userName){
        instance.userName = userName;
    }

    public String getUserType(){
        return instance.userType;
    }

    public String getUserName(){
        return instance.userName;
    }
}

