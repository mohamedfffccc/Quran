package com.example.quranonline.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.quranonline.R;
import com.example.quranonline.view.fragment.azkar.AzkarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;

public class AzkarActivity extends BaseActivity {

    @BindView(R.id.azkar_activity_bu_sabah)
    Button azkarActivityBuSabah;
    @BindView(R.id.azkar_activity_bu_soir)
    Button azkarActivityBuSoir;
    @BindView(R.id.azkar_activity_bu_sleep)
    Button azkarActivityBuSleep;
    @BindView(R.id.azkar_activity_bu_listen)
    Button azkarActivityBuListen;
    @BindView(R.id.relative_home)
   public RelativeLayout relativeHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar);

        ButterKnife.bind(this);
        relativeHome.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.azkar_activity_bu_sabah, R.id.azkar_activity_bu_soir, R.id.azkar_activity_bu_sleep, R.id.azkar_activity_bu_listen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.azkar_activity_bu_sabah:
                break;
            case R.id.azkar_activity_bu_soir:
                break;
            case R.id.azkar_activity_bu_sleep:
                break;
            case R.id.azkar_activity_bu_listen:
                ReplaceFragment(getSupportFragmentManager(), new AzkarFragment(), R.id.azkar_id, null, "medo");

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.superBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
