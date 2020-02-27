
package com.example.quranonline.data.model.ayat;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ayat {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("transliteration_en")
    @Expose
    private String transliterationEn;
    @SerializedName("translation_en")
    @Expose
    private String translationEn;
    @SerializedName("total_verses")
    @Expose
    private Integer totalVerses;
    @SerializedName("revelation_type")
    @Expose
    private String revelationType;
    @SerializedName("verses")
    @Expose
    private List<Verse> verses = null;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransliterationEn() {
        return transliterationEn;
    }

    public void setTransliterationEn(String transliterationEn) {
        this.transliterationEn = transliterationEn;
    }

    public String getTranslationEn() {
        return translationEn;
    }

    public void setTranslationEn(String translationEn) {
        this.translationEn = translationEn;
    }

    public Integer getTotalVerses() {
        return totalVerses;
    }

    public void setTotalVerses(Integer totalVerses) {
        this.totalVerses = totalVerses;
    }

    public String getRevelationType() {
        return revelationType;
    }

    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }

}
