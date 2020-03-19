package com.example.quranonline.view.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.quranonline.R;
import com.example.quranonline.view.activity.MainActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.quranonline.data.local.HelperMethod.shareVia;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends BaseFragment {
    public ProgressDialog dialog;
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
    MainActivity ma;
    @BindView(R.id.btn_download)
    ImageView btnDownload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_player, container, false);
        ButterKnife.bind(this, root);
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

    @OnClick({R.id.btn_download , R.id.player_btnplay, R.id.player_btstop, R.id.facebook, R.id.whatsup, R.id.twitter, R.id.instagram, R.id.pinteret})
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
            case R.id.btn_download :
                CheckUserPermsions();
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
    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        new DownloadTask(getActivity()).execute(path);

    }
    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new DownloadTask(getActivity()).execute(path);
                } else {
                    // Permission Denied
                    Toast.makeText( getActivity(),"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    public void showProgressDialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("downloading...");
        dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
    }

    public class DownloadTask extends AsyncTask<String, Integer, String> {
        private Context context;
        private PowerManager.WakeLock mwakelook;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mwakelook = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mwakelook.acquire();
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream("/sdcard/OnlineQurn_Downloads/"+surah_num+"_"+server_name+".mp3");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            mwakelook.release();
            dialog.dismiss();
            if (result != null)
                Toast.makeText(context,"Download error: "+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            dialog.setIndeterminate(false);
            dialog.setMax(100);
            dialog.setProgress(progress[0]);
        }

    }
}
