package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ciwbm on 5/15/17.
 */

public class CoffeeStatus
{
    @SerializedName("err")
    @Expose
    private String err;
    @SerializedName("status")
    @Expose
    private Status status;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
