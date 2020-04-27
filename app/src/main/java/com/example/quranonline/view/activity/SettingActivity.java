package com.example.quranonline.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.quranonline.R;
import com.example.quranonline.data.service.AzkarService;
import com.example.quranonline.data.service.KhafSurahServicer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.Constants.AZKAR;
import static com.example.quranonline.data.local.Constants.KHAF_ALARM;
import static com.example.quranonline.data.local.Constants.PERIOD;
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
    @BindView(R.id.ativity_setting_tv_surah_alkahf)
    TextView ativitySettingTvSurahAlkahf;
    @BindView(R.id.activity_setting_sw_surah_kahf)
    Switch activitySettingSwSurahKahf;
    private Intent azkar_i;
    private Intent khaf_i;
    SimpleDateFormat simpleDateFormat;
    String day_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setSharedPreferences(SettingActivity.this);
        simpleDateFormat=new SimpleDateFormat("EEEE" , Locale.ENGLISH);
        day_name=simpleDateFormat.format(new Date());
        Log.d("day" , day_name);
        try {
            if (LoadData(SettingActivity.this, AZKAR).equals("true")) {
                activitySettingSw.setChecked(true);
                activitySettingLinPercentage.setVisibility(View.VISIBLE);
                try {
                    int interval = Integer.parseInt(LoadData(SettingActivity.this, PERIOD));
                    switch (interval) {
                        case 5:
                            activitySettingRbHigh.setChecked(true);
                            break;
                        case 15:
                            activitySettingRbMedium.setChecked(true);
                            break;
                        case 30:
                            activitySettingRbRare.setChecked(true);
                            break;
                    }
                } catch (Exception e) {

                }
            } else if (LoadData(SettingActivity.this, AZKAR).equals("false")) {
                activitySettingSw.setChecked(false);
                activitySettingRbHigh.setChecked(false);
                activitySettingRbMedium.setChecked(false);
                activitySettingRbRare.setChecked(false);
            }
        } catch (Exception e) {

        }
        try {
            if (LoadData(SettingActivity.this , KHAF_ALARM).equals("true")) {
                activitySettingSwSurahKahf.setChecked(true);
            }
            else
            if (LoadData(SettingActivity.this , KHAF_ALARM).equals("false")) {
                activitySettingSwSurahKahf.setChecked(false);
            }

        }
        catch (Exception e)
        {

        }

        activitySettingSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SaveData(SettingActivity.this, AZKAR, "true");
                    activitySettingLinPercentage.setVisibility(View.VISIBLE);
                    Animation set = AnimationUtils.loadAnimation(SettingActivity.this, R.anim.fade_anim);
                    activitySettingLinPercentage.startAnimation(set);

                } else {
                    SaveData(SettingActivity.this, AZKAR, "false");
                    activitySettingLinPercentage.setVisibility(View.GONE);

                    cancelAzkar();
                    SaveData(SettingActivity.this , PERIOD , String.valueOf(0));



                }
            }
        });
        activitySettingRgPercentagr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {


                    case R.id.activity_setting_rb_high:
                        period = 5;
                        SaveData(SettingActivity.this, PERIOD, String.valueOf(5));


                        break;
                    case R.id.activity_setting_rb_medium:
                        period = 15;
                        SaveData(SettingActivity.this, PERIOD, String.valueOf(15));


                        break;
                    case R.id.activity_setting_rb_rare:
                        period = 30;
                        SaveData(SettingActivity.this, PERIOD, String.valueOf(30));


                        break;
                }

            }
        });
        activitySettingSwSurahKahf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingActivity.this , "تم تفعيل قؤاءة سورة الكهف يوم الجمعة" , Toast.LENGTH_SHORT).show();
                    SaveData(SettingActivity.this , KHAF_ALARM , "true");
                    startKuhfService();
                }
                else if (!isChecked) {
                    SaveData(SettingActivity.this , KHAF_ALARM , "false");

                    cancelKhafService();
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
        // T

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                intterval * 60 * 1000, pendingIntent);
    }

    @OnClick(R.id.activity_setting_btn_save)
    public void onViewClicked() {

        startAlarmService(period);
        Log.d("PERIOD", Integer.valueOf(LoadData(this, PERIOD)) + "");
        Toast.makeText(this, "تم ضبط الاذكار كل " + period + " دقائق  ", Toast.LENGTH_SHORT).show();


    }
    public void startKuhfService() {
        khaf_i = new Intent(SettingActivity.this, KhafSurahServicer.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, khaf_i, 0);
        Calendar calendar = Calendar.getInstance();
        int days = Calendar.FRIDAY + (7-calendar.get(Calendar.DAY_OF_WEEK));
        Log.d("time" , days+"");
        calendar.set(Calendar.DATE , days);
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),7*24*60*60*1000 ,
                  pendingIntent);
    }
    public void cancelAzkar()
    {
        Intent i  = new Intent(SettingActivity.this, AzkarService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this , "تم ايقاف خدمة الازكار" , Toast.LENGTH_SHORT).show();




    }
    public void cancelKhafService()
    {
        Intent i  = new Intent(SettingActivity.this, KhafSurahServicer.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this , "تم ايقاف الخدمة " , Toast.LENGTH_SHORT).show();




    }
}
