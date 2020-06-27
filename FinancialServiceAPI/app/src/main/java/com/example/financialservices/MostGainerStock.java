
package com.example.financialservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostGainerStock {

    @SerializedName("ticker")
    @Expose
    private String ticker;
    @SerializedName("changes")
    @Expose
    private Double changes;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("changesPercentage")
    @Expose
    private String changesPercentage;
    @SerializedName("companyName")
    @Expose
    private String companyName;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getChanges() {
        return changes;
    }

    public void setChanges(Double changes) {
        this.changes = changes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangesPercentage() {
        return changesPercentage;
    }

    public void setChangesPercentage(String changesPercentage) {
        this.changesPercentage = changesPercentage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
