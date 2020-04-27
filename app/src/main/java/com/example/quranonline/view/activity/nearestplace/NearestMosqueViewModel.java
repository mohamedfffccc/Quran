package com.example.quranonline.view.activity.nearestplace;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranonline.data.model.places.Places;
import com.google.android.gms.maps.model.LatLng;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quranonline.data.api.AyahClient.getNearestPlace;

public class NearestMosqueViewModel extends ViewModel {
    public MutableLiveData<Places> data = new MutableLiveData<>();
    public void getNearestData(
            String latLng ,int radius , String type , String name , String key
    )
    {
        getNearestPlace().getNearestMosque(
                latLng , radius , type , name , key
        ).enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                data.setValue(response.body());
                Log.d("results" , response.body().getResults().size()+"");
                Log.d("status" , response.body().getStatus());
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {

            }
        });
    }
}
