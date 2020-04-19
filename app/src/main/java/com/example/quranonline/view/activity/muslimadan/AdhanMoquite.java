package com.example.quranonline.view.activity.muslimadan;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quranonline.R;
import com.example.quranonline.data.model.salat.salat.Salat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Locale.ENGLISH;

public class AdhanMoquite extends AppCompatActivity implements SensorEventListener {

    @BindView(R.id.activity_adan_tv_day)
    TextView activityAdanTvDay;
    @BindView(R.id.activity_adan_tv_date)
    TextView activityAdanTvDate;
    @BindView(R.id.fajr_tv)
    TextView fajrTv;
    @BindView(R.id.sunset_tv)
    TextView sunsetTv;
    @BindView(R.id.duhr_tv)
    TextView duhrTv;
    @BindView(R.id.asr_tv)
    TextView asrTv;
    @BindView(R.id.magrib_tv)
    TextView magribTv;
    @BindView(R.id.isha_tv)
    TextView ishaTv;
    @BindView(R.id.lin_salat)
    LinearLayout linSalat;
    @BindView(R.id.iv_compass)
    ImageView ivCompass;
    @BindView(R.id.activity_adan_tv_date_name)
    TextView activityAdanTvDateName;
    private AdanViewModel viewModel;
    private SimpleDateFormat simpleDateFormat;
    private String date_of_day;
    private String my_address;
    private int calc_method;
    SimpleDateFormat day;
    private float currentDegree = 0f;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhan_moquite);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(AdanViewModel.class);
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
        date_of_day = simpleDateFormat.format(new Date());
        activityAdanTvDateName.setText(date_of_day);
        my_address = "Cairo,EG";
        calc_method = 5;
        day = new SimpleDateFormat("EEEE");
        String day_name = day.format(new Date());
        activityAdanTvDate.setText(day_name);
        setData();
    }

    private void setData() {
        viewModel.getAdanData(date_of_day, my_address, calc_method);
        viewModel.data.observe(this, new Observer<Salat>() {
            @Override
            public void onChanged(Salat salat) {
                fajrTv.setText("الفجر                 " + salat.getData().getTimings().getFajr() + " ص ");
                sunsetTv.setText("الشروق               " + salat.getData().getTimings().getSunrise() + " ص ");
                duhrTv.setText("الــظهــر                 " + setTimings(salat.getData().getTimings().getDhuhr()) + " ص ");

                asrTv.setText("الــعصــر                  " + setTimings(salat.getData().getTimings().getAsr()) + " م ");
                magribTv.setText("المغرب               " + setTimings(salat.getData().getTimings().getMaghrib()) + "م ");
                ishaTv.setText("العشاء                " + setTimings(salat.getData().getTimings().getIsha()) + " م ");

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(120);
        ra.setFillAfter(true);
        ivCompass.startAnimation(ra);
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
    public String setTimings(String time)
    {
        String [] times = time.split(":");
        if (Integer.parseInt(times[0])>=12)
        {
            int p = Integer.parseInt(times [0]) - 12;
            return  p+":"+times[1];
        }
        return  time;
    }
}
