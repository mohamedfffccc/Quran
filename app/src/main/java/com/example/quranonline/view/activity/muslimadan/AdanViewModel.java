package com.example.quranonline.view.activity.muslimadan;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranonline.data.model.salat.salat.Salat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quranonline.data.api.AyahClient.getAdan;

public class AdanViewModel extends ViewModel {
   public MutableLiveData<Salat> data = new MutableLiveData<>();
    public void getAdanData(String date , String address , int method)
    {
        getAdan().getSalat(date , address , method).enqueue(new Callback<Salat>() {
            @Override
            public void onResponse(Call<Salat> call, Response<Salat> response) {
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Salat> call, Throwable t) {

            }
        });
    }
}
