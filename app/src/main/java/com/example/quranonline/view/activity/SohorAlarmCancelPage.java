package com.example.quranonline.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranonline.R;
import com.example.quranonline.data.service.SohorAlarmsSrvice;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class SohorAlarmCancelPage extends AppCompatActivity {

    @BindView(R.id.iv_gras)
    GifImageView ivGras;
    @BindView(R.id.cancel_alarm_btn)
    CircleImageView cancelAlarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sohor_alarm_cancel_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cancel_alarm_btn)
    public void onViewClicked() {
        stopService(new Intent(this, SohorAlarmsSrvice.class));
        finish();
    }
}
