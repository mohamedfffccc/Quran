package com.example.quranonline.view.activity.adanmonth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Locale.ENGLISH;

public class AdanMonth extends AppCompatActivity implements SensorEventListener {

    @BindView(R.id.prayings_rv)
    RecyclerView prayingsRv;
    LinearLayoutManager linearLayoutManager;
    TimingAdapter adapter;
    AdanMonthViewModel viewModel;
    @BindView(R.id.imv_compass)
    ImageView imvCompass;
    private SimpleDateFormat simpleDateFormat;
    private String[] date_split;
    int month;
    int year;
    int method = 5;
    String address = "Cairo,Egypt";
    List<Datum> prayings = new ArrayList<>();
    private float currentDegree = 0f;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adan_month2);
        ButterKnife.bind(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
        date_split = simpleDateFormat.format(new Date()).split("-");
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        prayingsRv.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this).get(AdanMonthViewModel.class);
        viewModel.getPrayings(address, method, date_split[1], date_split[2]);
        viewModel.data.observe(this, new Observer<SalatList>() {
            @Override
            public void onChanged(SalatList data) {
                for (int i = 0; i < data.getData().size(); i++) {
                    prayings.addAll(Collections.singleton(data.getData().get(i)));

                }
                adapter = new TimingAdapter(AdanMonth.this, prayings, AdanMonth.this);
                prayingsRv.setAdapter(adapter);
                SnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(prayingsRv);

                prayingsRv.scrollToPosition(getPosition());
            }
        });
    }

    public int getPosition() {
        int position;

        position = Integer.parseInt(date_split[0]);
        return position - 1;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(120);
        ra.setFillAfter(true);
        imvCompass.startAnimation(ra);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }
}
