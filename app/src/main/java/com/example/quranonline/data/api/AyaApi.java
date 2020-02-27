package com.example.quranonline.data.api;

import com.example.quranonline.data.model.ayat.Ayat;
import com.example.quranonline.data.model.tafseer.Tafseer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AyaApi {
    @GET("quran-json@1.0.1/json/surahs/{surahNumber}.json")
    public Call<Ayat> getSurah(@Path("surahNumber") int surahNumber);
    @GET("tafseer/{tafseer_id}/{sura_number}/{ayah_number}/")
    public Call<Tafseer> getquranTafseer(@Path("tafseer_id") int tafseer_id ,
                                         @Path("sura_number") int sura_number ,
                                         @Path("ayah_number") int ayah_number);
}
