package com.example.quranonline.view.fragment.ayah;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranonline.R;
import com.example.quranonline.adapter.AyahAdapter;
import com.example.quranonline.data.model.ayat.Verse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AyahFragment extends Fragment {

    LinearLayoutManager linearLayout;
    @BindView(R.id.ayarv)
    RecyclerView ayarv;
    AyahAdapter adapter;
    public int surahNumber;
    AyahViewModel viewModel;
    String tafseer;
String tafseerText;
    private String data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ayah, container, false);
        ButterKnife.bind(this, v);
        linearLayout = new LinearLayoutManager(getActivity());
//        linearLayout.setOrientation(RecyclerView.HORIZONTAL);
        ayarv.setLayoutManager(linearLayout);
        viewModel= ViewModelProviders.of(this).get(AyahViewModel.class);
        viewModel.getData(surahNumber);
        viewModel.data.observe(this, new Observer<List<Verse>>() {
            @Override
            public void onChanged(List<Verse> verses) {
                adapter=new AyahAdapter(getActivity() , verses,AyahFragment.this,surahNumber);
                ayarv.setAdapter(adapter);
            }
        });
        return v;
    }
    public String getTafseerData(int tafseer_id,int surah_num,int aya_num)
    {
        viewModel.getQuranTafseer(tafseer_id,surah_num,aya_num);
        viewModel.data2.observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {

                        tafseer = s;

                    }
                });


//
        return tafseer;

}

}
