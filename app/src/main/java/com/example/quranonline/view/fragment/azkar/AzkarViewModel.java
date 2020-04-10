package com.example.quranonline.view.fragment.azkar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranonline.data.model.Azkar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quranonline.data.api.AyahClient.getAzkarData;

public class AzkarViewModel extends ViewModel {
    MutableLiveData<List<Azkar>> data = new MutableLiveData<>();
    public void getAzkars()
    {
        getAzkarData().getAzkar().enqueue(new Callback<List<Azkar>>() {
            @Override
            public void onResponse(Call<List<Azkar>> call, Response<List<Azkar>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Azkar>> call, Throwable t) {

            }
        });
    }

}
