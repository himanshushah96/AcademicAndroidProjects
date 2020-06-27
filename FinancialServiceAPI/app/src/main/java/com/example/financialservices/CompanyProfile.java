package com.example.financialservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyProfile {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("profile")
    @Expose
    private Profile_ profile;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Profile_ getProfile() {
        return profile;
    }

    public void setProfile(Profile_ profile) {
        this.profile = profile;
    }

}


 class Profile_ {

    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("beta")
    @Expose
    private String beta;
    @SerializedName("volAvg")
    @Expose
    private String volAvg;
    @SerializedName("mktCap")
    @Expose
    private String mktCap;
    @SerializedName("lastDiv")
    @Expose
    private String lastDiv;
    @SerializedName("range")
    @Expose
    private String range;
    @SerializedName("changes")
    @Expose
    private Double changes;
    @SerializedName("changesPercentage")
    @Expose
    private String changesPercentage;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("exchange")
    @Expose
    private String exchange;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("ceo")
    @Expose
    private String ceo;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("image")
    @Expose
    private String image;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getVolAvg() {
        return volAvg;
    }

    public void setVolAvg(String volAvg) {
        this.volAvg = volAvg;
    }

    public String getMktCap() {
        return mktCap;
    }

    public void setMktCap(String mktCap) {
        this.mktCap = mktCap;
    }

    public String getLastDiv() {
        return lastDiv;
    }

    public void setLastDiv(String lastDiv) {
        this.lastDiv = lastDiv;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Double getChanges() {
        return changes;
    }

    public void setChanges(Double changes) {
        this.changes = changes;
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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}