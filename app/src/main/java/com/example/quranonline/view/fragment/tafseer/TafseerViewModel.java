package com.example.quranonline.view.fragment.tafseer;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quranonline.data.model.tafseer.Tafseer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quranonline.data.api.AyahClient.getTafseer;

public class TafseerViewModel extends ViewModel {


    MutableLiveData<List<Tafseer>> data = new MutableLiveData<>();
    public void getData(int tafseer_id,int surah_num,int aya_from,int aya_to)
    {
        try{
            getTafseer().getTafseerData(tafseer_id,surah_num,aya_from,aya_to).enqueue(new Callback<List<Tafseer>>() {
                @Override
                public void onResponse(Call<List<Tafseer>> call, Response<List<Tafseer>> response) {
                    try{
                        data.setValue(response.body());

                    }
                    catch (Exception e)
                    {

                    }
                }

                @Override
                public void onFailure(Call<List<Tafseer>> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {

        }

    }
}
