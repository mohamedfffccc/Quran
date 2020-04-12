package com.example.quranonline;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.SharedPreferencesManger.LoadData;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;
import static com.example.quranonline.data.local.SharedPreferencesManger.setSharedPreferences;

public class SettingActivity extends AppCompatActivity {
    public int period;

    @BindView(R.id.activity_setting_tv_title)
    TextView activitySettingTvTitle;
    @BindView(R.id.ativity_setting_tv_azkar)
    TextView ativitySettingTvAzkar;
    @BindView(R.id.activity_setting_sw)
    Switch activitySettingSw;
    @BindView(R.id.activity_setting_rb_high)
    RadioButton activitySettingRbHigh;
    @BindView(R.id.activity_setting_rb_medium)
    RadioButton activitySettingRbMedium;
    @BindView(R.id.activity_setting_rb_rare)
    RadioButton activitySettingRbRare;
    @BindView(R.id.activity_setting_rg_percentagr)
    RadioGroup activitySettingRgPercentagr;
    @BindView(R.id.activity_setting_btn_save)
    Button activitySettingBtnSave;
    @BindView(R.id.activity_setting_lin_percentage)
    LinearLayout activitySettingLinPercentage;
    private Intent azkar_i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSharedPreferences(SettingActivity.this);
        try{
            if (LoadData(SettingActivity.this  ,"azkar").equals("true"))
            {
                activitySettingSw.setChecked(true);
                activitySettingLinPercentage.setVisibility(View.VISIBLE);
                try{
                    int interval = Integer.parseInt(LoadData(SettingActivity.this , "period"));
                    switch (interval)
                    {
                        case 5 :
                            activitySettingRbHigh.setChecked(true);
                            break;
                        case 15 :
                            activitySettingRbMedium.setChecked(true);
                            break;
                        case 30 :
                            activitySettingRbRare.setChecked(true);
                            break;
                    }
                }
                catch (Exception e)
                {

                }
 }
            else
            {
                activitySettingSw.setChecked(false);
            }
        }
        catch (Exception e)
        {

        }

        activitySettingSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SaveData(SettingActivity.this , "azkar" , "true");
                    activitySettingLinPercentage.setVisibility(View.VISIBLE);
                    Animation set = AnimationUtils.loadAnimation(SettingActivity.this , R.anim.fade_anim);
                    activitySettingLinPercentage.startAnimation(set);

                }
                else
                {
                    activitySettingLinPercentage.setVisibility(View.GONE);


                }
            }
        });
        activitySettingRgPercentagr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {


                    case R.id.activity_setting_rb_high:
                        period = 5;
                        SaveData(SettingActivity.this , "period" , String.valueOf(5));
                        break;
                    case R.id.activity_setting_rb_medium:
                        period = 15;
                        SaveData(SettingActivity.this , "period" , String.valueOf(15));

                        break;
                    case R.id.activity_setting_rb_rare:
                        SaveData(SettingActivity.this , "period" , String.valueOf(30));

                        period = 30;
                        break;
                }

            }
        });
    }

    public void startAlarmService(int intterval) {
         azkar_i = new Intent(SettingActivity.this, AzkarService.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, azkar_i, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String time = simpleDateFormat.format(new Date());
        String[] times = time.split(":");
        Toast.makeText(this, times[0] + times[1], Toast.LENGTH_SHORT).show();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                intterval* 60 * 1000, pendingIntent);
    }

    @OnClick(R.id.activity_setting_btn_save)
    public void onViewClicked() {
        if (activitySettingSw.isChecked()==true)
        {
            startAlarmService(period);
        }
        else if(activitySettingSw.isChecked()==false)
        {
            stopService(new Intent(SettingActivity.this , AzkarService.class));
        }


    }
}
