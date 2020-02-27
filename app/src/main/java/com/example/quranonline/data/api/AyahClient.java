package com.example.quranonline.data.api;

import com.example.quranonline.data.api.AyaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AyahClient {
    public static Retrofit retrofit,retrofit2;
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
}
