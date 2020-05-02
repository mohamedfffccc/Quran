package com.example.quranonline.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranonline.R;
import com.example.quranonline.data.local.SharedPreferencesManger;
import com.github.eltohamy.materialhijricalendarview.CalendarDay;
import com.github.eltohamy.materialhijricalendarview.MaterialHijriCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quranonline.data.local.SharedPreferencesManger.loadPrayings;

public class HigriCalender extends AppCompatActivity {

    @BindView(R.id.higri_calv)
    MaterialHijriCalendarView higriCalv;
    SimpleDateFormat format = new SimpleDateFormat("EEEE");
    @BindView(R.id.higri_tvname)
    TextView higriTvname;
    @BindView(R.id.higri_tvdate)
    TextView higriTvdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higri_calender);
        ButterKnife.bind(this);
        higriCalv.setSelected(true);
        String day_name = format.format(new Date());
        higriTvname.setText(day_name);
        SimpleDateFormat sdf = new SimpleDateFormat("dd" , Locale.ENGLISH);
        int day_num = Integer.parseInt(sdf.format(new Date()));
        higriTvdate.setText(loadPrayings(HigriCalender.this).getData().get(day_num-1).getDate().getHijri().getDate());

    }
}
