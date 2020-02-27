
package com.example.quranonline.data.model.ayat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verse {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("translation_en")
    @Expose
    private String translationEn;
    @SerializedName("translation_id")
    @Expose
    private String translationId;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslationEn() {
        return translationEn;
    }

    public void setTranslationEn(String translationEn) {
        this.translationEn = translationEn;
    }

    public String getTranslationId() {
        return translationId;
    }

    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

}
