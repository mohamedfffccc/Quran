package com.example.quranonline.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.quranonline.R;
import com.example.quranonline.adapter.ViewPagerWithFragmentAdapter;
import com.example.quranonline.view.fragment.MenuFragment;
import com.example.quranonline.view.fragment.QiblaFragment;
import com.example.quranonline.view.fragment.adan.Adan;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quranonline.data.local.HelperMethod.setTabs;

public class HomePageActivIty extends BaseActivity {

    @BindView(R.id.restaurantorders_tabhome)
  public   TabLayout restaurantordersTabhome;
    @BindView(R.id.restaurantorders_viewhome)
   public ViewPager restaurantordersViewhome;
    private ViewPagerWithFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_activ_ity);
        ButterKnife.bind(this);
        CheckUserPermsions();
        restaurantordersViewhome.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(restaurantordersTabhome));
        adapter = new ViewPagerWithFragmentAdapter(getSupportFragmentManager());
        adapter.addPager(new MenuFragment(), "", 1);
        adapter.addPager(new Adan(), "", 2);
        adapter.addPager(new QiblaFragment(), "", 3);
        restaurantordersViewhome.setAdapter(adapter);
        restaurantordersTabhome.setupWithViewPager(restaurantordersViewhome);
        setTabs(restaurantordersTabhome);

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

    @Override
    public void onBackPressed() {
        super.superBackPressed();
    }
}
