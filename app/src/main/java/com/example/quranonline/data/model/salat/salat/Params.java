
package com.example.quranonline.data.model.salat.salat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("Fajr")
    @Expose
    private Double fajr;
    @SerializedName("Isha")
    @Expose
    private Double isha;

    public Double getFajr() {
        return fajr;
    }

    public void setFajr(Double fajr) {
        this.fajr = fajr;
    }

    public Double getIsha() {
        return isha;
    }

    public void setIsha(Double isha) {
        this.isha = isha;
    }

}
