package com.example.quranonline.view.fragment.ayah;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranonline.data.model.ayat.Ayat;
import com.example.quranonline.data.model.ayat.Verse;
import com.example.quranonline.data.model.tafseer.Tafseer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quranonline.data.api.AyahClient.getClient;
import static com.example.quranonline.data.api.AyahClient.getTafseer;

public class AyahViewModel extends ViewModel {
    MutableLiveData<List<Verse>> data = new MutableLiveData<>();
    MutableLiveData<String> data2=new MutableLiveData<>();
    public void getData(int surahNumber)
    {
        getClient().getSurah(surahNumber).enqueue(new Callback<Ayat>() {
            @Override
            public void onResponse(Call<Ayat> call, Response<Ayat> response) {
                data.setValue(response.body().getVerses());
            }

            @Override
            public void onFailure(Call<Ayat> call, Throwable t) {

            }
        });

    }
    public void getQuranTafseer(int tafseer_id,int surahnumbe,int aua_number)
    {
        getTafseer().getquranTafseer(tafseer_id,surahnumbe,aua_number).enqueue(new Callback<Tafseer>() {
            @Override
            public void onResponse(Call<Tafseer> call, Response<Tafseer> response) {
                data2.setValue(response.body().getText());
            }

            @Override
            public void onFailure(Call<Tafseer> call, Throwable t) {

            }
        });
    }
}
