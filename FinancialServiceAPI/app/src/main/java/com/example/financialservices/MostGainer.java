
package com.example.financialservices;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostGainer {

    @SerializedName("mostGainerStock")
    @Expose
    private List<MostGainerStock> mostGainerStock = null;

    public List<MostGainerStock> getMostGainerStock() {
        return mostGainerStock;
    }

    public void setMostGainerStock(List<MostGainerStock> mostGainerStock) {
        this.mostGainerStock = mostGainerStock;
    }

}
