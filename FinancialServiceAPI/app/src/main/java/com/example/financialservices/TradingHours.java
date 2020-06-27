
package com.example.financialservices;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradingHours {

    @SerializedName("stockExchangeName")
    @Expose
    private String stockExchangeName;
    @SerializedName("stockMarketHours")
    @Expose
    private StockMarketHours stockMarketHours;
    @SerializedName("stockMarketHolidays")
    @Expose
    private List<StockMarketHoliday> stockMarketHolidays = null;
    @SerializedName("isTheStockMarketOpen")
    @Expose
    private Boolean isTheStockMarketOpen;

    public String getStockExchangeName() {
        return stockExchangeName;
    }

    public void setStockExchangeName(String stockExchangeName) {
        this.stockExchangeName = stockExchangeName;
    }

    public StockMarketHours getStockMarketHours() {
        return stockMarketHours;
    }

    public void setStockMarketHours(StockMarketHours stockMarketHours) {
        this.stockMarketHours = stockMarketHours;
    }

    public List<StockMarketHoliday> getStockMarketHolidays() {
        return stockMarketHolidays;
    }

    public void setStockMarketHolidays(List<StockMarketHoliday> stockMarketHolidays) {
        this.stockMarketHolidays = stockMarketHolidays;
    }

    public Boolean getIsTheStockMarketOpen() {
        return isTheStockMarketOpen;
    }

    public void setIsTheStockMarketOpen(Boolean isTheStockMarketOpen) {
        this.isTheStockMarketOpen = isTheStockMarketOpen;
    }

}
