package com.example.quranonline.view.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranonline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.quranonline.data.local.Constants.SOUND;
import static com.example.quranonline.data.local.SharedPreferencesManger.LoadData;
import static com.example.quranonline.data.local.SharedPreferencesManger.SaveData;

public class ElectronicSebha extends AppCompatActivity {

    @BindView(R.id.activity_electronic_tv_count)
    TextView activityElectronicTvCount;
    @BindView(R.id.activity_electronic_btn_start)
    CircleImageView activityElectronicBtnStart;
    @BindView(R.id.activity_electronic_btn_reset)
    CircleImageView activityElectronicBtnReset;
    public int count = 0;
    boolean sound;
    @BindView(R.id.btn_volume)
    ImageView btnVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_sebha);
        ButterKnife.bind(this);
        SaveData(this, SOUND, "enabled");
        sound = true;
    }

    @OnClick({R.id.activity_electronic_btn_start, R.id.btn_volume, R.id.activity_electronic_btn_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_electronic_btn_start:
                count = count + 1;
                MediaPlayer mp_start = MediaPlayer.create(getApplicationContext(), R.raw.tick);
                if (LoadData(this,SOUND).equals("enabled")) {
                    new SoundPlay(mp_start).start();
                }
                activityElectronicTvCount.setText(String.valueOf(count));
                break;
            case R.id.activity_electronic_btn_reset:
                MediaPlayer mp_reset = MediaPlayer.create(getApplicationContext(), R.raw.restt);
                if (LoadData(this,SOUND).equals("enabled")) {
                    new SoundPlay(mp_reset).start();
                }
                count = 0;
                activityElectronicTvCount.setText(String.valueOf(count));
                break;
            case R.id.btn_volume:
                if (sound == true) {
                    SaveData(this, SOUND, "disabled");
                    btnVolume.setImageResource(R.drawable.ic_volume_off_black_24dp);
                    sound=false;


                }
                else if (sound==false) {
                    SaveData(this, SOUND, "enabled");
                    btnVolume.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    sound=true;

                }

        }
    }

    class SoundPlay extends Thread {
        MediaPlayer mp;

        public SoundPlay(MediaPlayer mp) {
            this.mp = mp;
        }

        public void run() {
            mp.start();

        }
    }
}
