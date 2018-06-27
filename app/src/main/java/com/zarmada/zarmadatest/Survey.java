package com.zarmada.zarmadatest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Survey {

    @Expose
    @SerializedName("id")
    private int id;


    @Expose
    @SerializedName("quality")
    private int quantity;


    @Expose
    @SerializedName("expertise")
    private int expertise;


    @Expose
    @SerializedName("culture")
    private int culture;


    @Expose
    @SerializedName("average")
    private double average;


    @Expose
    @SerializedName("comments")
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExpertise() {
        return expertise;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }

    public int getCulture() {
        return culture;
    }

    public void setCulture(int culture) {
        this.culture = culture;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
