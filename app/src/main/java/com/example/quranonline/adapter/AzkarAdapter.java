package com.example.quranonline.adapter;


import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quranonline.R;
import com.example.quranonline.data.model.Azkar;
import com.example.quranonline.data.service.DownloadTask;
import com.example.quranonline.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by medo on 13/11/2016.
 */

public class AzkarAdapter extends RecyclerView.Adapter<AzkarAdapter.CategoriesViewHolder> {
    public MediaPlayer mp = new MediaPlayer();


    int id;


    private Context context;
    //    private BaseActivity activity;
    private List<Azkar> categoryList = new ArrayList<>();
    MainActivity mainActivity;


    public AzkarAdapter(Context context, List<Azkar> categoryList, MainActivity mainActivity) {
        this.context = context;
        this.categoryList = categoryList;
        this.mainActivity = mainActivity;


    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.azkar_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Azkar categorydata = categoryList.get(position);
        Glide.with(context).load(categorydata.getReader_img()).into(holder.readerIvimg);
        holder.teaderTvname.setText(categorydata.getReader_name());
        holder.teaderTvtitle.setText(categorydata.getName());
        holder.teaderBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(context, "/sdcard/OnlineQurn_Downloads/" + "azkar" +
                        categoryList.get(position).Name +
                        "_" + ".mp3").execute(categoryList.get(position).getLink());
            }
        });
        holder.teaderBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.readerSeek.setVisibility(View.VISIBLE);
                Thread play_mp3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mp.setDataSource(categoryList.get(position).getLink());
                            mp.prepare();
                            mp.start();


                        } catch (Exception e) {

                        }
                        holder.readerSeek.setMax(mp.getDuration());

                        while (true) {
                            try {
                                Thread.sleep(1000);


                            } catch (Exception e) {

                            }
                            if (context == null) {
                                return;
                            }
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    holder.readerSeek.setProgress(mp.getCurrentPosition());


                                }
                            });
                        }

                    }
                });

                play_mp3.start();

            }
        });
        holder.readerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());

            }
        });
        holder.teaderBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();

            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @OnClick({R.id.teader_btn_play, R.id.teader_btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teader_btn_play:
                break;
            case R.id.teader_btn_download:
                break;
        }
    }


    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reader_ivimg)
        CircleImageView readerIvimg;
        @BindView(R.id.teader_tvname)
        TextView teaderTvname;
        @BindView(R.id.teader_tvtitle)
        TextView teaderTvtitle;
        @BindView(R.id.teader_btn_play)
        ImageView teaderBtnPlay;
        @BindView(R.id.teader_btn_download)
        ImageView teaderBtnDownload;
        @BindView(R.id.reader_seek)
        SeekBar readerSeek;
        @BindView(R.id.teader_btn_pause)
        ImageView teaderBtnPause;


        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
