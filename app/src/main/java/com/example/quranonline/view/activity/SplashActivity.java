package com.example.quranonline.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.pr_loading)
    ProgressBar prLoading;

    @BindView(R.id.linear_check_internet)
    LinearLayout linearCheckInternet;
    @BindView(R.id.btn_try_again)
    Button btnTryAgain;
    @BindView(R.id.btn_continue)
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prLoading.setVisibility(View.VISIBLE);

            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isNetworkAvailable(SplashActivity.this);

                //


            }
        }, 3000);

    }


    public boolean isNetworkAvailable(Context context) {
        if (context == null) return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true;
                    }
                }
            } else {

                try {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        Log.i("update_statut", "Network is available : true");
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        return true;
                    }
                } catch (Exception e) {
                    Log.i("update_statut", "" + e.getMessage());
                }
            }
        }
        Log.i("update_statut", "Network is available : FALSE ");
//        Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
        Animation set = AnimationUtils.loadAnimation(this, R.anim.bottom_top);
        linearCheckInternet.setVisibility(View.VISIBLE);
        linearCheckInternet.startAnimation(set);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);

        return false;
    }


    @OnClick({R.id.btn_try_again, R.id.btn_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_try_again:
                startActivity(new Intent(this , SplashActivity.class));

                break;
            case R.id.btn_continue:
                startActivity(new Intent(this , MainActivity.class));

                break;
        }
    }
}
