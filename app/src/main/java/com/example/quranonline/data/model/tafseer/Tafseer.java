
package com.example.quranonline.data.model.tafseer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tafseer {

    @SerializedName("tafseer_id")
    @Expose
    private Integer tafseerId;
    @SerializedName("tafseer_name")
    @Expose
    private String tafseerName;
    @SerializedName("ayah_url")
    @Expose
    private String ayahUrl;
    @SerializedName("ayah_number")
    @Expose
    private Integer ayahNumber;
    @SerializedName("text")
    @Expose
    private String text;

    public Integer getTafseerId() {
        return tafseerId;
    }

    public void setTafseerId(Integer tafseerId) {
        this.tafseerId = tafseerId;
    }

    public String getTafseerName() {
        return tafseerName;
    }

    public void setTafseerName(String tafseerName) {
        this.tafseerName = tafseerName;
    }

    public String getAyahUrl() {
        return ayahUrl;
    }

    public void setAyahUrl(String ayahUrl) {
        this.ayahUrl = ayahUrl;
    }

    public Integer getAyahNumber() {
        return ayahNumber;
    }

    public void setAyahNumber(Integer ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
