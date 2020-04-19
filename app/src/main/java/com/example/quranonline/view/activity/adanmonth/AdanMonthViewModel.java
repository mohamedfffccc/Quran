package com.example.quranonline.view.activity.adanmonth;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranonline.data.model.salatlist.Datum;
import com.example.quranonline.data.model.salatlist.SalatList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quranonline.data.api.AyahClient.getAdanMonth;

public class AdanMonthViewModel extends ViewModel {
    MutableLiveData<List<Datum>> data = new MutableLiveData<>();
    public void getPrayings(String address , int method , String month , String year)
    {
        getAdanMonth().getTimings(address , method , month , year).enqueue(new Callback<SalatList>() {
            @Override
            public void onResponse(Call<SalatList> call, Response<SalatList> response) {
                data.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<SalatList> call, Throwable t) {

            }
        });
    }
}
