package com.example.quranonline.view.fragment;


import android.animation.AnimatorSet;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.quranonline.R;
import com.example.quranonline.data.model.Azkar;
import com.example.quranonline.data.service.DownloadTask;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.quranonline.data.local.HelperMethod.shareVia;

/**
 * A simple {@link Fragment} subclass.
 */
public class AzkarPlayer extends BaseFragment {
    MediaPlayer mp = new MediaPlayer();



    @BindView(R.id.player_tv_surah_name)
    TextView playerTvSurahName;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.btn_download)
    ImageView btnDownload;
    @BindView(R.id.playr_ivpreview)
    ImageView playrIvpreview;
    @BindView(R.id.rel1)
    RelativeLayout rel1;
    @BindView(R.id.player_sbprogress)
    SeekBar playerSbprogress;
    @BindView(R.id.player_btnplay)
    ImageView playerBtnplay;
    @BindView(R.id.player_btstop)
    ImageView playerBtstop;
    @BindView(R.id.player_btshare)
    ImageView playerBtshare;
    @BindView(R.id.facebook)
    CircleImageView facebook;
    @BindView(R.id.whatsup)
    CircleImageView whatsup;
    ///////////
    @BindView(R.id.twitter)
    CircleImageView twitter;
    @BindView(R.id.instagram)
    CircleImageView instagram;
    @BindView(R.id.pinteret)
    CircleImageView pinteret;
    String facebooku = "com.facebook.katana";
    String twitteru = "com.twitter.android";
    String instagramu = "com.instagram.android";
    String pinterestu = "com.pinterest";
    String whatsupu = "com.whatsapp";

   public Azkar azkar;
    private Player player = new Player();
    private AnimationDrawable drawable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, v);
        playerTvSurahName.setText(azkar.getName() + "\n" + azkar.getReader_name());
        setUpActivity();
        player.start();
        drawable = (AnimationDrawable) playrIvpreview.getBackground();

        playerSbprogress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mp != null) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        return v;
    }
    @OnClick({R.id.btn_download, R.id.player_btnplay, R.id.player_btstop, R.id.facebook, R.id.whatsup, R.id.twitter, R.id.instagram, R.id.pinteret})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.player_btnplay:
                mp.start();
                drawable.start();
//
//
//
                break;
            case R.id.player_btstop:
                mp.pause();
                drawable.stop();

//
                break;
            case R.id.facebook:
                shareVia(getActivity(), facebooku, azkar.getLink());
                break;
            case R.id.whatsup:
                shareVia(getActivity(), whatsupu, azkar.getLink());
                break;
            case R.id.twitter:
                shareVia(getActivity(), twitteru, azkar.getLink());
                break;
            case R.id.instagram:
                shareVia(getActivity(), instagramu, azkar.getLink());
                break;
            case R.id.pinteret:
                shareVia(getActivity(), pinterestu, azkar.getLink());
                break;
            case R.id.btn_download:
                new DownloadTask(getActivity(),
                        "/sdcard/OnlineQurn_Downloads/" + azkar.getName() + "_" + azkar.getReader_name() + ".mp3")
                        .execute(azkar.getLink());

                break;
        }
    }
    class Player extends Thread {
        @Override
        public void run() {
            try {
                mp.setDataSource(azkar.getLink());
                try {
                    mp.prepare();
                } catch (Exception e) {
                }
                playerSbprogress.setMax(mp.getDuration());

            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (getActivity() == null) {
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        playerSbprogress.setProgress(mp.getCurrentPosition());
                    }
                });
            }
        }
    }

}
