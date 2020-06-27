package a.m.carrental.db;

import java.util.ArrayList;

public class DbModel {
    private static ArrayList<User> users;
    private static ArrayList<Car> cars;
    private static ArrayList<Booking> bookings;

    private static DbModel instance = null;

    private DbModel(){
        users = new ArrayList<User>();
        cars = new ArrayList<Car>();
        bookings = new ArrayList<Booking>();
    }

    public static DbModel getInstance(){
        if(instance == null){
            instance = new DbModel();
            SetupData setupData=new SetupData();
        }
        return instance;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public ArrayList<Car> getCars(){
        return cars;
    }

    public ArrayList<Booking> getBookings() { return bookings; }

    public void add(User user){
        users.add(user);
    }

    public void add(Booking booking) { bookings.add(booking); }

    public void add(Car car){
        cars.add(car);
    }

}
