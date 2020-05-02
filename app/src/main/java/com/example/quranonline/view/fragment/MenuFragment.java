package com.example.quranonline.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.example.quranonline.R;
import com.example.quranonline.view.activity.DoaaKtemQuran;
import com.example.quranonline.view.activity.ElectronicSebha;
import com.example.quranonline.view.activity.HigriCalender;
import com.example.quranonline.view.activity.HomePageActivIty;
import com.example.quranonline.view.activity.SettingActivity;
import com.example.quranonline.view.activity.SohorAlarm;
import com.example.quranonline.view.activity.nearestplace.NearestMosqueActivity;
import com.example.quranonline.view.fragment.azkar.AzkarFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quranonline.data.local.Constants.ACTION;
import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment {


    @BindView(R.id.share_btn)
    AppCompatImageView shareBtn;
    @BindView(R.id.val_btn)
    AppCompatImageView valBtn;
    @BindView(R.id.listenazkarbtn)
    AppCompatImageView listenazkarbtn;
    @BindView(R.id.listenbtn)
    AppCompatImageView listenbtn;
    @BindView(R.id.readbtn)
    AppCompatImageView readbtn;
    @BindView(R.id.salat_btn)
    AppCompatImageView salatBtn;
    @BindView(R.id.qibla_btn)
    AppCompatImageView qiblaBtn;
    @BindView(R.id.electronic_btn)
    AppCompatImageView electronicBtn;
    @BindView(R.id.doaa_btn)
    AppCompatImageView doaaBtn;
    @BindView(R.id.btn_setting)
    AppCompatImageView btnSetting;
    @BindView(R.id.nearest_mosque_btn)
    AppCompatImageView nearestMosqueBtn;
    @BindView(R.id.sohor_alarm_btn)
    AppCompatImageView sohorAlarmBtn;
    public HomePageActivIty activIty;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, root);
        activIty=(HomePageActivIty) getActivity();
        return root;
    }


    @OnClick({R.id.share_btn,R.id.higri_btn ,  R.id.val_btn, R.id.listenazkarbtn, R.id.listenbtn, R.id.readbtn, R.id.salat_btn, R.id.qibla_btn, R.id.electronic_btn, R.id.doaa_btn, R.id.btn_setting, R.id.nearest_mosque_btn, R.id.sohor_alarm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_btn:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "قم بتنزيل هذا التطبيق الرائع");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=com.NEWS.quranonline");
                sendIntent.setType("img/plain");
                getActivity().startActivity(Intent.createChooser(sendIntent, ""));
                break;
            case R.id.val_btn:
                Intent val_i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.NEWS.quranonline"));
                getActivity().startActivity(val_i);
                break;
            case R.id.listenazkarbtn:
                ReplaceFragment(getActivity().getSupportFragmentManager(), new AzkarFragment(), R.id.main, null, "medo");

                break;
            case R.id.listenbtn:
                SaveData(getActivity(), ACTION, "listen");
                createDir();
                ReplaceFragment(getActivity().getSupportFragmentManager(), new AuthorFragment(), R.id.main, null, "medo");

                break;
            case R.id.readbtn:
                SaveData(getActivity(), ACTION, "read");
                ReplaceFragment(getActivity().getSupportFragmentManager(), new SurahFragment(), R.id.main, null, "medo");
                break;
            case R.id.salat_btn:
                activIty.restaurantordersViewhome.setCurrentItem(1);
                break;
            case R.id.qibla_btn:
                activIty.restaurantordersViewhome.setCurrentItem(2);

                break;
            case R.id.electronic_btn:
                Intent elec_i = new Intent(getActivity(), ElectronicSebha.class);
                getActivity().startActivity(elec_i);
                break;
            case R.id.doaa_btn:
                Intent khetm_i = new Intent(getActivity(), DoaaKtemQuran.class);
                getActivity().startActivity(khetm_i);
                break;
            case R.id.btn_setting:
                Intent setting_i = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(setting_i);
                break;
            case R.id.nearest_mosque_btn:
                Intent location_i = new Intent(getActivity(), NearestMosqueActivity.class);
                getActivity().startActivity(location_i);
                break;
            case R.id.sohor_alarm_btn:
                Intent sohor_i = new Intent(getActivity(), SohorAlarm.class);
                getActivity().startActivity(sohor_i);
                break;
            case R.id.higri_btn :
                Intent higri_i = new Intent(getActivity(), HigriCalender.class);
                getActivity().startActivity(higri_i);
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

    @Override
    public void onBack() {
        super.onBack();
    }
}
