package com.example.quranonline.data.api;

import com.example.quranonline.data.api.AyaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AyahClient {
    public static Retrofit retrofit,retrofit2,retrofit3;
    public static AyaApi getClient()
    {
        retrofit=new Retrofit.Builder().baseUrl("https://unpkg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(AyaApi.class);

    }
    public static  AyaApi getTafseer()
    {
        retrofit2=new Retrofit.Builder().baseUrl("http://api.quran-tafseer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit2.create(AyaApi.class);
    }
    public static AyaApi getAzkarData()
    {
        retrofit3=new Retrofit.Builder()
                .baseUrl("https://mostafagad9090.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit3.create(AyaApi.class);
    }
}
