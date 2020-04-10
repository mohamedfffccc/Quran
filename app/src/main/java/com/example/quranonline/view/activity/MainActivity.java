package com.example.quranonline.view.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
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
import androidx.core.app.ActivityCompat;

import com.example.quranonline.R;
import com.example.quranonline.view.fragment.AuthorFragment;
import com.example.quranonline.view.fragment.PlayerFragment;
import com.example.quranonline.view.fragment.SurahFragment;
import com.example.quranonline.view.fragment.azkar.AzkarFragment;
import com.example.quranonline.view.fragment.tafseer.TafseerFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;

public class MainActivity extends BaseActivity {

    @BindView(R.id.readbtn)
    ImageView readbtn;
    @BindView(R.id.listenbtn)
    ImageView listenbtn;
    @BindView(R.id.tafseerglalin)
    ImageView tafseerglalin;
    @BindView(R.id.linear)
    LinearLayout linear;

    @BindView(R.id.main)
    RelativeLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CheckUserPermsions();


        Animation set2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
        linear.setVisibility(View.VISIBLE);
        linear.startAnimation(set2);
    }

//    @Override
//    public void onBackPressed() {
//        showDialoge();
//    }

    @OnClick({R.id.readbtn, R.id.listenbtn, R.id.tafseerglalin , R.id.listenazkarbtn})
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
            case  R.id.listenazkarbtn :
                ReplaceFragment(getSupportFragmentManager(), new AzkarFragment(), R.id.main, null, "medo");

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
    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
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
                    Toast.makeText( this,"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
    public  void  showDialoge()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("تنبيه")
                .setMessage("هل تريد الخروج من البرتامج ؟")
                .setIcon(R.drawable.logo_app);
        builder.setPositiveButton("معم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
}
