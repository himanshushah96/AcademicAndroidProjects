
package com.example.financialservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockMarketHours {

    @SerializedName("openingHour")
    @Expose
    private String openingHour;
    @SerializedName("closingHour")
    @Expose
    private String closingHour;

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }

}
