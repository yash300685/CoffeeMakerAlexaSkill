package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ciwbm on 5/15/17.
 */

public class Status {
    @SerializedName("numCups")
    @Expose
    private Integer numCups;
    @SerializedName("percWater")
    @Expose
    private Integer percWater;
    @SerializedName("percBeans")
    @Expose
    private Integer percBeans;

    public Integer getNumCups() {
        return numCups;
    }

    public void setNumCups(Integer numCups) {
        this.numCups = numCups;
    }

    public Integer getPercWater() {
        return percWater;
    }

    public void setPercWater(Integer percWater) {
        this.percWater = percWater;
    }

    public Integer getPercBeans() {
        return percBeans;
    }

    public void setPercBeans(Integer percBeans) {
        this.percBeans = percBeans;
    }

}
