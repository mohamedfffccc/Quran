package com.example.quranonline.view.fragment.tafseer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranonline.R;
import com.example.quranonline.adapter.TafseerAdapter;
import com.example.quranonline.data.model.tafseer.Tafseer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quranonline.data.local.HelperMethod.dismissProgressDialog;
import static com.example.quranonline.data.local.HelperMethod.showProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class TafseerFragment extends Fragment {
    public int surah_num;
    public  int aya_from,aya_to;

    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.tafseerrv)
    RecyclerView tafseerrv;
    TafseerAdapter adapter;
    TafseerViewModel viewModel;

    public TafseerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tafseer, container, false);
        Toast.makeText(getActivity(),"from "+aya_from+ "to"+aya_to , Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this, root);
        viewModel = ViewModelProviders.of(this).get(TafseerViewModel.class);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        tafseerrv.setLayoutManager(linearLayoutManager);
        getData();
        viewModel.data.observe(this, new Observer<List<Tafseer>>() {
            @Override
            public void onChanged(List<Tafseer> tafseers) {
                dismissProgressDialog();
                adapter=new TafseerAdapter(getActivity() , tafseers);
                tafseerrv.setAdapter(adapter);

            }
        });

        return root;
    }
    public void getData()
    {
        showProgressDialog(getActivity() , "please wait");
        viewModel.getData(2,surah_num,aya_from,aya_to);

    }

}
