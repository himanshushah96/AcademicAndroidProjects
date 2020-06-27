
package com.example.financialservices;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostLoser {

    @SerializedName("mostLoserStock")
    @Expose
    private List<MostLoserStock> mostLoserStock = null;

    public List<MostLoserStock> getMostLoserStock() {
        return mostLoserStock;
    }

    public void setMostLoserStock(List<MostLoserStock> mostLoserStock) {
        this.mostLoserStock = mostLoserStock;
    }

}
