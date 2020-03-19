package com.example.quranonline.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.quranonline.R;
import com.example.quranonline.view.fragment.AuthorFragment;
import com.example.quranonline.view.fragment.SurahFragment;

import java.io.File;

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
    @BindView(R.id.tafseerglalin)
    Button tafseerglalin;
    @BindView(R.id.linear)
    LinearLayout linear;

    @BindView(R.id.main)
    RelativeLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        linear.setVisibility(View.GONE);

        @SuppressLint("ResourceType") Animation set = AnimationUtils.loadAnimation(this, R.animator.scaleimage);
        splashIvlogo.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                        Toast.makeText(getApplicationContext() , "choose action" , Toast.LENGTH_SHORT).show();

                Animation  set2 = AnimationUtils.loadAnimation(MainActivity.this , R.anim.translate);
                linear.setVisibility(View.VISIBLE);
//
                linear.startAnimation(set2);

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

    @OnClick({R.id.readbtn, R.id.listenbtn, R.id.tafseerglalin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.readbtn:
                SaveData(MainActivity.this, "action", "read");
                ReplaceFragment(getSupportFragmentManager(), new SurahFragment(), R.id.main, null, "medo");

                break;
            case R.id.listenbtn:
                SaveData(MainActivity.this, "action", "listen");
                createDir();
                ReplaceFragment(getSupportFragmentManager(), new AuthorFragment(), R.id.main, null, "medo");

                break;
            case R.id.tafseerglalin:
                SaveData(MainActivity.this, "action", "tafseer");
                ReplaceFragment(getSupportFragmentManager(), new SurahFragment(), R.id.main, null, "medo");

                break;
        }


    }
    public void createDir()
    {
        File file = new File(Environment.getExternalStorageDirectory() , "/OnlineQurn_Downloads");
        boolean success = true;
        if (!file.exists()) {
        file.mkdir();

        }
    }



}
