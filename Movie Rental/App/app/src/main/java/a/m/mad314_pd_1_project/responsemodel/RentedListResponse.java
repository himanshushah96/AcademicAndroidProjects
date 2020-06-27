package a.m.mad314_pd_1_project.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RentedListResponse {

        @SerializedName("movieName")
        @Expose
        private String movieName;
        @SerializedName("rentId")
        @Expose
        private Integer rentId;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("rentPrice")
        @Expose
        private Double rentPrice;
        @SerializedName("rentedDate")
        @Expose
        private String rentedDate;
        @SerializedName("dueDate")
        @Expose
        private String dueDate;

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public Integer getRentId() {
            return rentId;
        }

        public void setRentId(Integer rentId) {
            this.rentId = rentId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Double getRentPrice() {
            return rentPrice;
        }

        public void setRentPrice(Double rentPrice) {
            this.rentPrice = rentPrice;
        }

        public String getRentedDate() {
            return rentedDate;
        }

        public void setRentedDate(String rentedDate) {
            this.rentedDate = rentedDate;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

    }
