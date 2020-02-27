package com.example.quranonline.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.quranonline.R;
import com.example.quranonline.view.fragment.SurahFragment;
import com.example.quranonline.view.fragment.AuthorFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;

public class MainActivity extends BaseActivity {

    @BindView(R.id.splash_ivlogo)
    ImageView splashIvlogo;
    @BindView(R.id.readbtn)
    Button readbtn;
    @BindView(R.id.listenbtn)
    Button listenbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        @SuppressLint("ResourceType") Animation set = AnimationUtils.loadAnimation(this, R.animator.scaleimage);
        splashIvlogo.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.readbtn, R.id.listenbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.readbtn:
                SaveData(MainActivity.this , "action" , "read");
                ReplaceFragment(getSupportFragmentManager(),new SurahFragment() ,R.id.main , null , "medo");

                break;
            case R.id.listenbtn:
                SaveData(MainActivity.this , "action" , "listen");
                ReplaceFragment(getSupportFragmentManager(),new AuthorFragment() ,R.id.main , null , "medo");

                break;
        }

    }

}
