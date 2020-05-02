package com.example.quranonline.view.fragment.adan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quranonline.R;
import com.example.quranonline.adapter.TimingAdapter;
import com.example.quranonline.data.model.salatlist.Datum;
import com.example.quranonline.data.model.salatlist.SalatList;
import com.example.quranonline.view.fragment.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.quranonline.data.local.Constants.ADAN_SERVICE;
import static com.example.quranonline.data.local.Constants.ASR;
import static com.example.quranonline.data.local.Constants.DUHR;
import static com.example.quranonline.data.local.Constants.FAJR;
import static com.example.quranonline.data.local.Constants.ISHAA;
import static com.example.quranonline.data.local.Constants.MAGRIB;
import static com.example.quranonline.data.local.HelperMethod.startAlarmService;
import static com.example.quranonline.data.local.SharedPreferencesManger.LoadData;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;
import static com.example.quranonline.data.local.SharedPreferencesManger.loadPrayings;
import static com.example.quranonline.data.local.SharedPreferencesManger.save;
import static java.util.Locale.ENGLISH;

/**
 * A simple {@link Fragment} subclass.
 */
public class Adan extends BaseFragment {
    @BindView(R.id.prayings_rv)
    RecyclerView prayingsRv;
    LinearLayoutManager linearLayoutManager;
    TimingAdapter adapter;
    AdanMonthViewModel viewModel;

    @BindView(R.id.sw_adan)
    Switch swAdan;
    @BindView(R.id.adan_service_tv)
    TextView adanServiceTv;
    @BindView(R.id.reload_btn)
    CircleImageView reloadBtn;
    private SimpleDateFormat simpleDateFormat;
    private String[] date_split;
    int month;
    int year;
    int method = 5;
    String address = "Cairo,Egypt";
    List<Datum> prayings = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_adan_month2, container, false);
        ButterKnife.bind(this , root);
        SimpleDateFormat sdf = new SimpleDateFormat("dd", ENGLISH);
        int value = Integer.parseInt(sdf.format(new Date()));
        try {
            if (LoadData(getActivity(), ADAN_SERVICE).equals("true")) {
                swAdan.setChecked(true);
                adanServiceTv.setText("خدمة الاذان مفعلة");

            } else if (LoadData(getActivity(), ADAN_SERVICE).equals("false")) {
                swAdan.setChecked(false);
                adanServiceTv.setText("خدمة الاذان غير مفعلة");

            }
        } catch (Exception e) {

        }


        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
        date_split = simpleDateFormat.format(new Date()).split("-");
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        prayingsRv.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(getActivity()).get(AdanMonthViewModel.class);
        savePrayings();
        initRecycler();


        swAdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adanServiceTv.setText("خدمة الاذان مفعلة");
                    SaveData(getActivity(), ADAN_SERVICE, "true");
                    try {
                        startAlarmService(getActivity(), LoadData(getActivity(), FAJR));
                        startAlarmService(getActivity(), LoadData(getActivity(), DUHR));
                        startAlarmService(getActivity(), LoadData(getActivity(), ASR));
                        startAlarmService(getActivity(), LoadData(getActivity(), MAGRIB));
                        startAlarmService(getActivity(), LoadData(getActivity(), ISHAA));
                    } catch (Exception e) {

                    }

                    Log.d("STARTED", "adan");
                    Toast.makeText(getActivity(), "خدمة الاذان مفعلة", Toast.LENGTH_SHORT).show();
                } else {
                    adanServiceTv.setText("خدمة الاذان غير مفعلة");
                    SaveData(getActivity(), ADAN_SERVICE, "false");
                    Toast.makeText(getActivity(), "خدمة الاذان غير مفعلة", Toast.LENGTH_SHORT).show();

                }
            }
        });
        try {
            initRecycler();

            SaveData(getActivity(), FAJR, parse(loadPrayings(getActivity()).getData().get(value - 1).getTimings().getFajr()));
            SaveData(getActivity(), DUHR, parse(loadPrayings(getActivity()).getData().get(value - 1).getTimings().getDhuhr()));
            SaveData(getActivity(), ASR, parse(loadPrayings(getActivity()).getData().get(value - 1).getTimings().getAsr()));
            SaveData(getActivity(), MAGRIB, parse(loadPrayings(getActivity()).getData().get(value - 1).getTimings().getMaghrib()));
            SaveData(getActivity(), ISHAA, parse(loadPrayings(getActivity()).getData().get(value - 1).getTimings().getIsha()));

        } catch (Exception e) {

        }
        return root;

    }
    public int getPosition() {
        int position;

        position = Integer.parseInt(date_split[0]);
        return position - 1;

    }


    public String parse(String time) {
        int index = time.indexOf(" ");
        String s = time.substring(0, index);
        return s;
    }

    public void savePrayings() {
        viewModel.getPrayings(address, method, date_split[1], date_split[2]);
        viewModel.data.observe(this, new Observer<SalatList>() {
            @Override
            public void onChanged(SalatList data) {
                save(getActivity(), data);
                Log.d("SAVED", data.getData().toString());
                adapter = new TimingAdapter(getActivity(), loadPrayings(getActivity()).getData());
                prayingsRv.setAdapter(adapter);
                SnapHelper snapHelper = new PagerSnapHelper();
//                snapHelper.attachToRecyclerView(prayingsRv);

                prayingsRv.scrollToPosition(getPosition());


//
            }
        });
    }

    public void initRecycler() {
        if (loadPrayings(getActivity()) != null) {
            adapter = new TimingAdapter(getActivity(), loadPrayings(getActivity()).getData());
            prayingsRv.setAdapter(adapter);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(prayingsRv);

            prayingsRv.scrollToPosition(getPosition());
        }

    }


    @OnClick(R.id.reload_btn)
    public void onViewClicked() {

        try {

            save(getActivity(), null);
            savePrayings();
            // initRecycler();

        } catch (Exception e) {

        }
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}


