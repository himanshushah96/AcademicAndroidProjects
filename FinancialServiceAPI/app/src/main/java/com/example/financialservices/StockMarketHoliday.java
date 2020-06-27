
package com.example.financialservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockMarketHoliday {

    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("New Years Day")
    @Expose
    private String newYearsDay;
    @SerializedName("Martin Luther King, Jr. Day")
    @Expose
    private String martinLutherKingJrDay;
    @SerializedName("Washington's Birthday")
    @Expose
    private String washingtonSBirthday;
    @SerializedName("Good Friday")
    @Expose
    private String goodFriday;
    @SerializedName("Memorial Day")
    @Expose
    private String memorialDay;
    @SerializedName("Independence Day")
    @Expose
    private String independenceDay;
    @SerializedName("Labor Day")
    @Expose
    private String laborDay;
    @SerializedName("Thanksgiving Day")
    @Expose
    private String thanksgivingDay;
    @SerializedName("Christmas")
    @Expose
    private String christmas;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNewYearsDay() {
        return newYearsDay;
    }

    public void setNewYearsDay(String newYearsDay) {
        this.newYearsDay = newYearsDay;
    }

    public String getMartinLutherKingJrDay() {
        return martinLutherKingJrDay;
    }

    public void setMartinLutherKingJrDay(String martinLutherKingJrDay) {
        this.martinLutherKingJrDay = martinLutherKingJrDay;
    }

    public String getWashingtonSBirthday() {
        return washingtonSBirthday;
    }

    public void setWashingtonSBirthday(String washingtonSBirthday) {
        this.washingtonSBirthday = washingtonSBirthday;
    }

    public String getGoodFriday() {
        return goodFriday;
    }

    public void setGoodFriday(String goodFriday) {
        this.goodFriday = goodFriday;
    }

    public String getMemorialDay() {
        return memorialDay;
    }

    public void setMemorialDay(String memorialDay) {
        this.memorialDay = memorialDay;
    }

    public String getIndependenceDay() {
        return independenceDay;
    }

    public void setIndependenceDay(String independenceDay) {
        this.independenceDay = independenceDay;
    }

    public String getLaborDay() {
        return laborDay;
    }

    public void setLaborDay(String laborDay) {
        this.laborDay = laborDay;
    }

    public String getThanksgivingDay() {
        return thanksgivingDay;
    }

    public void setThanksgivingDay(String thanksgivingDay) {
        this.thanksgivingDay = thanksgivingDay;
    }

    public String getChristmas() {
        return christmas;
    }

    public void setChristmas(String christmas) {
        this.christmas = christmas;
    }

}
