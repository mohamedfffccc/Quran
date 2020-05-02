package com.example.quranonline.view.fragment;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.quranonline.R;
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
public class PlayerFragment extends BaseFragment {

    public String server_num, server_name, surah_num;
    String path;
    public String surah_name;
    boolean isplaying = false;
    Intent intent;
    MediaPlayer mp = new MediaPlayer();
    @BindView(R.id.playr_ivpreview)
    ImageView playrIvpreview;
    @BindView(R.id.player_sbprogress)
    SeekBar playerSbprogress;
    @BindView(R.id.player_btnplay)
    ImageView playerBtnplay;
    @BindView(R.id.player_btstop)
    ImageView playerBtstop;
    Player player = new Player();
    AnimationDrawable drawable;
    @BindView(R.id.player_btshare)
    ImageView playerBtshare;
    @BindView(R.id.facebook)
    CircleImageView facebook;
    @BindView(R.id.whatsup)
    CircleImageView whatsup;
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
    @BindView(R.id.btn_download)
    ImageView btnDownload;
    @BindView(R.id.player_tv_surah_name)
    TextView playerTvSurahName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, root);
        playerTvSurahName.setText("سورة " + surah_name);

        path = "https://server" + server_num + ".mp3quran.net/" + server_name + "/" + surah_num + ".mp3";
        ;
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


        return root;
    }

    @OnClick({R.id.btn_download, R.id.player_btnplay, R.id.player_btstop, R.id.facebook, R.id.whatsup, R.id.twitter, R.id.instagram, R.id.pinteret})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.player_btnplay:
                mp.start();
//
//
                drawable.start();
//
                break;
            case R.id.player_btstop:
                mp.pause();
                drawable.stop();
//
                break;
            case R.id.facebook:
                shareVia(getActivity(), facebooku, path);
                break;
            case R.id.whatsup:
                shareVia(getActivity(), whatsupu, path);
                break;
            case R.id.twitter:
                shareVia(getActivity(), twitteru, path);
                break;
            case R.id.instagram:
                shareVia(getActivity(), instagramu, path);
                break;
            case R.id.pinteret:
                shareVia(getActivity(), pinterestu, path);
                break;
            case R.id.btn_download:
                new DownloadTask(getActivity(),
                        "/sdcard/OnlineQurn_Downloads/" + surah_num + "_" + server_name + ".mp3")
                        .execute(path);

                break;
        }
    }

    class Player extends Thread {
        @Override
        public void run() {
            try {
                mp.setDataSource(path);
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

    @Override
    public void onBack() {

        player.interrupt();
        super.onBack();
    }


}
