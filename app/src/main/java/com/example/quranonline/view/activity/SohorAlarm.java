package com.example.quranonline.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranonline.R;
import com.example.quranonline.data.service.AzkarService;
import com.example.quranonline.data.service.SohorAlarmsSrvice;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.Constants.SOHOR_ALARM;
import static com.example.quranonline.data.local.Constants.SOHOR_HOUR;
import static com.example.quranonline.data.local.Constants.SOHOR_MINUTE;
import static com.example.quranonline.data.local.SharedPreferencesManger.LoadData;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;
import static com.example.quranonline.data.local.SharedPreferencesManger.setSharedPreferences;

public class SohorAlarm extends AppCompatActivity {

    @BindView(R.id.ativity_sohor_tv_alarm)
    TextView ativitySohorTvAlarm;
    @BindView(R.id.activity_sohor_sw)
    Switch activitySohorSw;
    @BindView(R.id.activity_sohor_tp)
    TimePicker activitySohorTp;
    @BindView(R.id.sohor_alarm_btn_sa)
    Button sohorAlarmBtnSa;
    private Intent sohor_i;
    int h, m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sohor_alarm);
        ButterKnife.bind(this);
        setSharedPreferences(this);
        try {
            if (LoadData(this, SOHOR_ALARM).equals("true")) {
                activitySohorSw.setChecked(true);
                activitySohorTp.setVisibility(View.VISIBLE);
                activitySohorTp.setHour(Integer.parseInt(LoadData(this , SOHOR_HOUR)));
                activitySohorTp.setMinute(Integer.valueOf(LoadData(this , SOHOR_MINUTE)));

            } else if (LoadData(this, SOHOR_ALARM).equals("false")) {
                activitySohorSw.setChecked(false);
                activitySohorTp.setVisibility(View.GONE);
            }
        } catch (Exception e) {

        }

        activitySohorSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SaveData(SohorAlarm.this, SOHOR_ALARM, "true");
                    activitySohorTp.setVisibility(View.VISIBLE);
                    Animation set = AnimationUtils.loadAnimation(SohorAlarm.this, R.anim.fade_anim);
                    activitySohorTp.startAnimation(set);

                } else if (!isChecked) {
                    cancelAlarm();
                    SaveData(SohorAlarm.this, SOHOR_ALARM, "false");
                    activitySohorTp.setVisibility(View.GONE);
                    SaveData(SohorAlarm.this, SOHOR_HOUR, String.valueOf(0));
                    SaveData(SohorAlarm.this, SOHOR_MINUTE, String.valueOf(0));


                }
            }
        });

        activitySohorTp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                SaveData(SohorAlarm.this, SOHOR_HOUR, String.valueOf(hourOfDay));
                m=minute;
                SaveData(SohorAlarm.this, SOHOR_MINUTE, String.valueOf(minute));

            }
        });
    }

    public void startSohorAlarm(int hour, int minute) {
        sohor_i = new Intent(SohorAlarm.this, SohorAlarmsSrvice.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, sohor_i, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
 }

    public void cancelAlarm() {
        Intent i = new Intent(SohorAlarm.this, SohorAlarmsSrvice.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "تم ايقاف منبه السحور", Toast.LENGTH_SHORT).show();


    }

    @OnClick(R.id.sohor_alarm_btn_sa)
    public void onViewClicked() {
        if (LoadData(this , SOHOR_ALARM).equals("true")) {
            startSohorAlarm(Integer.parseInt(LoadData(this , SOHOR_HOUR))
                    ,Integer.parseInt(LoadData(this , SOHOR_MINUTE)));
            Toast.makeText(this, "تم ضبط منبه السحور علي الساعة " + LoadData(this , SOHOR_MINUTE)
                    + " : " + LoadData(this , SOHOR_HOUR), Toast.LENGTH_SHORT).show();
        }
        }


}
