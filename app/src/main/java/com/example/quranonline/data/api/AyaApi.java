package com.example.quranonline.data.api;

import com.example.quranonline.data.model.Azkar;
import com.example.quranonline.data.model.ayat.Ayat;
import com.example.quranonline.data.model.places.Places;
import com.example.quranonline.data.model.salat.salat.Salat;
import com.example.quranonline.data.model.salatlist.SalatList;
import com.example.quranonline.data.model.tafseer.Tafseer;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AyaApi {
    @GET("quran-json@1.0.1/json/surahs/{surahNumber}.json")
    public Call<Ayat> getSurah(@Path("surahNumber") int surahNumber);
    @GET("tafseer/{tafseer_id}/{sura_number}/{ayah_number}/")
    public Call<Tafseer> getquranTafseer(@Path("tafseer_id") int tafseer_id ,
                                         @Path("sura_number") int sura_number ,
                                         @Path("ayah_number") int ayah_number);
    @GET("tafseer/{tafseer_id}/{sura_number}/{ayah_number_from}/{ayah_number_to}/")
    public Call<List<Tafseer>> getTafseerData (@Path("tafseer_id") int tafseer_id ,
                                             @Path("sura_number") int sura_number ,
                                             @Path("ayah_number_from") int ayah_number_from ,
                                             @Path("ayah_number_to") int ayah_number_to);
    @GET("QuranService/GetAzkar.php")
    Call<List<Azkar>> getAzkar();
    @GET("timingsByAddress/{date}")
   Call<Salat> getSalat(@Path("date") String date ,
                        @Query("address") String address ,
                        @Query("method") int method);
    @GET("calendarByAddress")
    Call<SalatList> getTimings(@Query("address") String address,
                               @Query("method") int method,
                               @Query("month") String month ,
                               @Query("year") String year);
    @GET("maps/api/place/nearbysearch/json")
    Call<Places> getNearestMosque(
                                  @Query("location")String location ,
                                  @Query("radius") int radius ,
                                  @Query("types") String types ,
                                  @Query("name") String name ,
                                  @Query("key") String key);

}
