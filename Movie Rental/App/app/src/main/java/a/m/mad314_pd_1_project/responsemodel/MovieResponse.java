package a.m.mad314_pd_1_project.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

        @SerializedName("movieId")
        @Expose
        private Integer movieId;
        @SerializedName("movieName")
        @Expose
        private String movieName;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("rentPrice")
        @Expose
        private Double rentPrice;

        public Integer getMovieId() {
            return movieId;
        }

        public void setMovieId(Integer movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public Double getRentPrice() {
            return rentPrice;
        }

        public void setRentPrice(Double rentPrice) {
            this.rentPrice = rentPrice;
        }

    }

