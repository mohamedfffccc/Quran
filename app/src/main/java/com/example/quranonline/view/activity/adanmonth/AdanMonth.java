package com.example.quranonline.view.activity.adanmonth;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.quranonline.R;
import com.example.quranonline.adapter.TimingAdapter;
import com.example.quranonline.data.model.salatlist.Datum;
import com.example.quranonline.data.model.salatlist.SalatList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

public class AdanMonth extends AppCompatActivity {

    @BindView(R.id.prayings_rv)
    RecyclerView prayingsRv;
    LinearLayoutManager linearLayoutManager;
    TimingAdapter adapter;
    AdanMonthViewModel viewModel;
    @BindView(R.id.imv_compass)
    ImageView imvCompass;
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
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adan_month2);
        ButterKnife.bind(this);

        SimpleDateFormat sdf = new SimpleDateFormat("dd", ENGLISH);
        int value = Integer.parseInt(sdf.format(new Date()));
        try {
            if (LoadData(this, ADAN_SERVICE).equals("true")) {
                swAdan.setChecked(true);
                adanServiceTv.setText("خدمة الاذان مفعلة");

            } else if (LoadData(this, ADAN_SERVICE).equals("false")) {
                swAdan.setChecked(false);
                adanServiceTv.setText("خدمة الاذان غير مفعلة");

            }
        } catch (Exception e) {

        }


        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
        date_split = simpleDateFormat.format(new Date()).split("-");
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        prayingsRv.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this).get(AdanMonthViewModel.class);
        savePrayings();
        initRecycler();


        swAdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adanServiceTv.setText("خدمة الاذان مفعلة");
                    SaveData(AdanMonth.this, ADAN_SERVICE, "true");
                    try {
                        startAlarmService(AdanMonth.this, LoadData(AdanMonth.this, FAJR));
                        startAlarmService(AdanMonth.this, LoadData(AdanMonth.this, DUHR));
                        startAlarmService(AdanMonth.this, LoadData(AdanMonth.this, ASR));
                        startAlarmService(AdanMonth.this, LoadData(AdanMonth.this, MAGRIB));
                        startAlarmService(AdanMonth.this, LoadData(AdanMonth.this, ISHAA));
                    } catch (Exception e) {

                    }

                    Log.d("STARTED", "adan");
                    Toast.makeText(AdanMonth.this, "خدمة الاذان مفعلة", Toast.LENGTH_SHORT).show();
                } else {
                    adanServiceTv.setText("خدمة الاذان غير مفعلة");
                    SaveData(AdanMonth.this, ADAN_SERVICE, "false");
                    Toast.makeText(AdanMonth.this, "خدمة الاذان غير مفعلة", Toast.LENGTH_SHORT).show();

                }
            }
        });
        try {
            initRecycler();

            SaveData(AdanMonth.this, FAJR, parse(loadPrayings(AdanMonth.this).getData().get(value - 1).getTimings().getFajr()));
            SaveData(AdanMonth.this, DUHR, parse(loadPrayings(AdanMonth.this).getData().get(value - 1).getTimings().getDhuhr()));
            SaveData(AdanMonth.this, ASR, parse(loadPrayings(AdanMonth.this).getData().get(value - 1).getTimings().getAsr()));
            SaveData(AdanMonth.this, MAGRIB, parse(loadPrayings(AdanMonth.this).getData().get(value - 1).getTimings().getMaghrib()));
            SaveData(AdanMonth.this, ISHAA, parse(loadPrayings(AdanMonth.this).getData().get(value - 1).getTimings().getIsha()));

        } catch (Exception e) {

        }
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
                save(AdanMonth.this, data);
                Log.d("SAVED", data.getData().toString());
                adapter = new TimingAdapter(AdanMonth.this, loadPrayings(AdanMonth.this).getData(), AdanMonth.this);
                prayingsRv.setAdapter(adapter);
                SnapHelper snapHelper = new PagerSnapHelper();
//                snapHelper.attachToRecyclerView(prayingsRv);

                prayingsRv.scrollToPosition(getPosition());


//
            }
        });
    }

    public void initRecycler() {
        if (loadPrayings(AdanMonth.this) != null) {
            adapter = new TimingAdapter(AdanMonth.this, loadPrayings(AdanMonth.this).getData(), AdanMonth.this);
            prayingsRv.setAdapter(adapter);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(prayingsRv);

            prayingsRv.scrollToPosition(getPosition());
        }

    }


    @OnClick(R.id.reload_btn)
    public void onViewClicked() {

        try {

                save(AdanMonth.this, null);
                savePrayings();
               // initRecycler();

        } catch (Exception e) {

        }
    }
}
