package com.example.quranonline.view.fragment.ayah;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import me.zhanghai.android.fastscroll.FastScrollerBuilder;

import static com.example.quranonline.data.local.HelperMethod.dismissProgressDialog;
import static com.example.quranonline.data.local.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class AyahFragment extends Fragment {
    public String name;

    LinearLayoutManager linearLayout;
    @BindView(R.id.ayarv)
    RecyclerView ayarv;
    AyahAdapter adapter;
    public int surahNumber;
    AyahViewModel viewModel;
    String tafseer;
    String tafseerText;
    @BindView(R.id.tv_surah_name)
    TextView tvSurahName;
    @BindView(R.id.tv_surah_type)
    TextView tvSurahType;
    @BindView(R.id.tv_surah_aya_nums)
    TextView tvSurahAyaNums;
    private String data;
    public String type;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ayah, container, false);
        ButterKnife.bind(this, v);
        tvSurahName.setText("سورة " + name);
        tvSurahType.setText(type);
        linearLayout = new LinearLayoutManager(getActivity());
//        linearLayout.setOrientation(RecyclerView.HORIZONTAL);
        ayarv.setLayoutManager(linearLayout);
        viewModel = ViewModelProviders.of(this).get(AyahViewModel.class);
        getSurahData();
        viewModel.data.observe(this, new Observer<List<Verse>>() {
            @Override
            public void onChanged(List<Verse> verses) {
                dismissProgressDialog();
                adapter = new AyahAdapter(getActivity(), verses, AyahFragment.this, surahNumber);
                ayarv.setAdapter(adapter);
                tvSurahAyaNums.setText(verses.size()+"");
            }
        });
        new FastScrollerBuilder(ayarv).build();
        return v;
    }

    public String getTafseerData(int tafseer_id, int surah_num, int aya_num) {
        viewModel.getQuranTafseer(tafseer_id, surah_num, aya_num);
        viewModel.data2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                tafseer = s;

            }
        });


//
        return tafseer;

    }

    public void getSurahData() {
        showProgressDialog(getActivity(), "please wait");
        viewModel.getData(surahNumber);


    }

}
