package a.m.carrental.model;

import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import a.m.carrental.db.Booking;
import a.m.carrental.db.Car;
import a.m.carrental.db.DbModel;

public class CarModel {
    
    public Car getCar(int id){
        DbModel db = DbModel.getInstance();
        ArrayList<Car> cars = db.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if(car.id == id){
                return car;
            }
        }
        return null;
    }

    private void setBookCar(int carId){
        DbModel db = DbModel.getInstance();
        ArrayList<Car> cars = db.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if(car.id == carId){
                car.isBooked = true;
            }
        }
    }

    private void unBookCar(int carId){
        DbModel db = DbModel.getInstance();
        ArrayList<Car> cars = db.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if(car.id == carId){
                car.isBooked = false;
            }
        }
    }

    private void setReserverCar(int carId){
        DbModel db = DbModel.getInstance();
        ArrayList<Car> cars = db.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if(car.id == carId){
                car.isReserved = true;
            }
        }
    }

    private void unsetReserverCar(int carId){
        DbModel db = DbModel.getInstance();
        ArrayList<Car> cars = db.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if(car.id == carId){
                car.isReserved = false;
            }
        }
    }

    public boolean cancelBooking(int carId) {
        DbModel db = DbModel.getInstance();
        ArrayList<Booking> bookings = db.getBookings();
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            if (booking.carId == carId) {
                bookings.remove(i);
                unBookCar(carId);
                return true;
            }

        }
        return false;
    }

    public boolean cancelReservation(int carId) {
        DbModel db = DbModel.getInstance();
        ArrayList<Booking> bookings = db.getBookings();
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            if (booking.carId == carId) {
                bookings.remove(i);
                unsetReserverCar(carId);
                return true;
            }

        }
        return false;
    }

    public boolean bookCar(int carId, String emailId, String fromDate, String toDate, boolean isForBooking){
        try{
            DbModel db = DbModel.getInstance();
            if(isCarAvailable(carId, fromDate, toDate)){
                Booking booking = new Booking();
                booking.carId = carId;
                booking.emailId = emailId;
                booking.startDate = fromDate;
                booking.endDate = toDate;
                if(isForBooking){
                    setBookCar(carId);
                }else{
                    setReserverCar(carId);
                }
                db.add(booking);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<Car> getUserBookedCar(){
        ArrayList<Car> bookedCars = new ArrayList<>();
        Session session = Session.getInstance();
        DbModel db = DbModel.getInstance();
        for (int i = 0; i < db.getBookings().size(); i++) {
            Booking booking = db.getBookings().get(i);
            if(Session.getInstance().getUserType().equals(Constants.USER_TYPE_EMPLOYEE) || booking.emailId.equals(session.getUserEmail())){
                Car car = getCar(booking.carId);
                if(car != null){
                    bookedCars.add(car);
                }
            }

        }
        return bookedCars;
    }

    public boolean isCarAvailable(int carId, String fromDate, String toDate){
        DbModel db = DbModel.getInstance();
        ArrayList<Car> cars = db.getCars();
        ArrayList<Booking> bookings = db.getBookings();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);


            if(car.id == carId && (car.isBooked || car.isReserved)){
                SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                for (int j = 0; j < bookings.size(); j++) {
                    try{
                        Booking booking = bookings.get(i);
                        if(
                                (booking.startDate.equals(fromDate) || booking.endDate.equals(toDate)) ||
                                booking.startDate.equals(toDate) || booking.endDate.equals(fromDate) ||
                                (df.parse(fromDate).after(df.parse(booking.startDate)) && df.parse(fromDate).before(df.parse(booking.endDate))) ||
                                (df.parse(toDate).after(df.parse(booking.startDate)) && df.parse(toDate).before(df.parse(booking.endDate)))
                        ){
                            return false;
                        }
                    }catch (ParseException e){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
