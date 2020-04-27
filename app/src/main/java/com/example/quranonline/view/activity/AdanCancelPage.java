package com.example.quranonline.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranonline.R;
import com.example.quranonline.data.service.AdanService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdanCancelPage extends AppCompatActivity {

    @BindView(R.id.tvsalat)
    TextView tvsalat;
    @BindView(R.id.adan_cancel_btn)
    ImageView adanCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adan_cancel_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.adan_cancel_btn)
    public void onViewClicked() {
        stopService(new Intent(this , AdanService.class));
        finish();
    }
}
