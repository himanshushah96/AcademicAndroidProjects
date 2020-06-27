package a.m.carrental.ui.home;

public class RecyclerViewItem {
    String carName;
    int carImage;
    double carPrice;
    String dateAvailable;



    public RecyclerViewItem(String carName,int carImage,double carPrice,String dateAvailable)
    {
        this.carName=carName;
        this.carImage=carImage;
        this.carPrice=carPrice;
        this.dateAvailable=dateAvailable;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarImage() {
        return carImage;
    }

    public void setCarImage(int carImage) {
        this.carImage = carImage;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }
}
