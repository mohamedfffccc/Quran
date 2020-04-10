package com.example.quranonline.view.fragment.azkar;


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
import com.example.quranonline.adapter.AzkarAdapter;
import com.example.quranonline.data.model.Azkar;
import com.example.quranonline.view.activity.MainActivity;
import com.example.quranonline.view.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AzkarFragment extends BaseFragment {
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.azkar_rv_azkar)
    RecyclerView azkarRvAzkar;
    AzkarAdapter adapter;
    MainActivity ma;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_azkar, container, false);
        ButterKnife.bind(this, root);
        ma=(MainActivity) getActivity();
        AzkarViewModel viewModel = ViewModelProviders.of(this).get(AzkarViewModel.class);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        azkarRvAzkar.setLayoutManager(linearLayoutManager);
        viewModel.getAzkars();
        viewModel.data.observe(this, new Observer<List<Azkar>>() {
            @Override
            public void onChanged(List<Azkar> azkars) {
                adapter=new AzkarAdapter(getActivity() , azkars , ma);
                azkarRvAzkar.setAdapter(adapter);

            }
        });
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onBack() {
        super.onBack();
        adapter.mp.stop();
    }
}
