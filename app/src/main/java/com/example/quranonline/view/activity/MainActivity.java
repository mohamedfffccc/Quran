package com.example.quranonline.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.quranonline.R;
import com.example.quranonline.view.activity.adanmonth.AdanMonth;
import com.example.quranonline.view.activity.muslimadan.AdhanMoquite;
import com.example.quranonline.view.fragment.AuthorFragment;
import com.example.quranonline.view.fragment.SurahFragment;
import com.example.quranonline.view.fragment.azkar.AzkarFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.Constants.ACTION;
import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;

public class MainActivity extends BaseActivity {

    @BindView(R.id.readbtn)
    Button readbtn;
    @BindView(R.id.listenbtn)
    Button listenbtn;
    @BindView(R.id.tafseerglalin)
    Button tafseerglalin;


    @BindView(R.id.main)
    RelativeLayout main;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.fragment_dealer_home_tv_departments)
    TextView fragmentDealerHomeTvDepartments;
    @BindView(R.id.listenazkarbtn)
    Button listenazkarbtn;
    @BindView(R.id.linear_options)
    LinearLayout linearOptions;
    @BindView(R.id.menu_btn)
    ImageView menuBtn;
    @BindView(R.id.exit_btn)
    Button exitBtn;
    @BindView(R.id.salat_btn)
    Button salatBtn;


    private Animation set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CheckUserPermsions();
        set = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xtranslate);
    }

    @OnClick({R.id.readbtn,R.id.electronic_btn ,R.id.sohor_alarm_btn ,   R.id.btn_setting, R.id.listenbtn, R.id.salat_btn, R.id.tafseerglalin, R.id.listenazkarbtn, R.id.exit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.readbtn:
                linearOptions.startAnimation(set);
                linearOptions.setVisibility(View.GONE);
                menuBtn.setImageResource(R.drawable.ic_menu_black_24dp);
                SaveData(MainActivity.this, ACTION, "read");
                ReplaceFragment(getSupportFragmentManager(), new SurahFragment(), R.id.main, null, "medo");

                break;
            case R.id.listenbtn:
                linearOptions.startAnimation(set);
                linearOptions.setVisibility(View.GONE);
                menuBtn.setImageResource(R.drawable.ic_menu_black_24dp);
                SaveData(MainActivity.this, ACTION, "listen");
                createDir();
                ReplaceFragment(getSupportFragmentManager(), new AuthorFragment(), R.id.main, null, "medo");

                break;
            case R.id.tafseerglalin:
                linearOptions.startAnimation(set);
                linearOptions.setVisibility(View.GONE);
                menuBtn.setImageResource(R.drawable.ic_menu_black_24dp);
                SaveData(MainActivity.this, ACTION, "tafseer");
                ReplaceFragment(getSupportFragmentManager(), new SurahFragment(), R.id.main, null, "medo");

                break;
            case R.id.listenazkarbtn:
                linearOptions.startAnimation(set);
                linearOptions.setVisibility(View.GONE);
                menuBtn.setImageResource(R.drawable.ic_menu_black_24dp);
                ReplaceFragment(getSupportFragmentManager(), new AzkarFragment(), R.id.main, null, "medo");

                break;
            case R.id.btn_setting:
                linearOptions.startAnimation(set);
                linearOptions.setVisibility(View.GONE);
                menuBtn.setImageResource(R.drawable.ic_menu_black_24dp);
                Intent setting_i = new Intent(this, SettingActivity.class);
                startActivity(setting_i);
                break;
            case R.id.exit_btn:
                showDialoge();
                break;
            case R.id.salat_btn:
                Intent salat_i = new Intent(this, AdanMonth.class);
                startActivity(salat_i);
                break;
            case  R.id.electronic_btn:
                Intent elec_i = new Intent(this, ElectronicSebha.class);
                startActivity(elec_i);
                break;
            case  R.id.sohor_alarm_btn :
                Intent sohor_i = new Intent(this, SohorAlarm.class);
                startActivity(sohor_i);
                break;


        }


    }

    public void createDir() {
        File file = new File(Environment.getExternalStorageDirectory(), "/OnlineQurn_Downloads");
        boolean success = true;
        if (!file.exists()) {
            file.mkdir();

        }
    }

    void CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }


    }

    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // Permission Denied
                    Toast.makeText(this, "your message", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    public void showDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("تنبيه")
                .setMessage("هل تريد الخروج من البرتامج ؟")
                .setIcon(R.drawable.logo_app);
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                finish();
            }
        })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.superBackPressed();
    }

    @OnClick(R.id.menu_btn)
    public void onViewClicked() {
        if (linearOptions.getVisibility() == View.GONE) {
            Animation set2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
            linearOptions.setVisibility(View.VISIBLE);
            linearOptions.startAnimation(set2);
            menuBtn.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
        } else if (linearOptions.getVisibility() == View.VISIBLE) {

            linearOptions.startAnimation(set);
            linearOptions.setVisibility(View.GONE);
            menuBtn.setImageResource(R.drawable.ic_menu_black_24dp);

        }

    }
}
