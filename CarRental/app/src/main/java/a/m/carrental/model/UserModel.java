package a.m.carrental.model;

import java.util.ArrayList;

import a.m.carrental.db.DbModel;
import a.m.carrental.db.User;

public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userType;

    public UserModel(String firstName, String lastName, String email, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public UserModel(){}

    public UserModel(String email) {
        this.email = email;
    }

    public UserModel(String email, String password, String userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public ArrayList<User> getUserBasedOnType(String userType) {
        ArrayList<User> customers = new ArrayList<User>();
        DbModel db = DbModel.getInstance();
        for ( int i = 0; i < db.getUsers().size(); i++) {
            User user = db.getUsers().get(i);
            if (user.userType.equals(userType)) {
                customers.add(user);
            }
        }
        return customers;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByEmail(String email){
        DbModel db = DbModel.getInstance();
        try{
            for (int i = 0; i < db.getUsers().size(); i++) {
                if(db.getUsers().get(i).email.equals(email)){
                    return db.getUsers().get(i);
                };
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static User Login(String email, String password, String userType){

        try{
            User user = getUserByEmail(email);
            if(user != null){
                if(user.password.equals(password) && user.userType.equals(userType)){
                    return  user;
                }else{
                    return null;
                }
            }else{
                return user;
            }

        }catch (Exception e){
            return  null;
        }
    }

    public static boolean Register(User user){
        DbModel db = DbModel.getInstance();

        try{
            db.add(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void updateUser(User user){
        DbModel dbModel = DbModel.getInstance();
        for (int i = 0; i < dbModel.getUsers().size(); i++) {
            User lastDetail = dbModel.getUsers().get(i);
            if(lastDetail.email.equals(user.email)){
                lastDetail.firstName = user.firstName;
                lastDetail.lastName = user.lastName;
            }
        }
    }

    public static boolean DeleteUser(String email)
    {
        DbModel db = DbModel.getInstance();
        try{
            for (int i = 0; i < db.getUsers().size(); i++) {
                if(db.getUsers().get(i).email.equals(email)){
                    db.getUsers().remove(i);
                    return true;
                };
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }

}
