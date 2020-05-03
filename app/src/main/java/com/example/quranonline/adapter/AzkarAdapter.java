package com.example.quranonline.adapter;


import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quranonline.R;
import com.example.quranonline.data.model.Azkar;
import com.example.quranonline.data.service.DownloadTask;
import com.example.quranonline.view.activity.AzkarActivity;
import com.example.quranonline.view.activity.HomePageActivIty;
import com.example.quranonline.view.fragment.AzkarPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;


/**
 * Created by medo on 13/11/2016.
 */

public class AzkarAdapter extends RecyclerView.Adapter<AzkarAdapter.CategoriesViewHolder> {
    public MediaPlayer mp = new MediaPlayer();


    int id;


    private Context context;
    //    private BaseActivity activity;
    private List<Azkar> categoryList = new ArrayList<>();
    AzkarActivity mainActivity;


    public AzkarAdapter(Context context, List<Azkar> categoryList, AzkarActivity mainActivity) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AzkarPlayer c = new AzkarPlayer();
                ReplaceFragment(mainActivity.getSupportFragmentManager(), c, R.id.azkar_id
            , null, "");
                c.azkar=categoryList.get(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }




    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reader_ivimg)
        CircleImageView readerIvimg;
        @BindView(R.id.teader_tvname)
        TextView teaderTvname;
        @BindView(R.id.teader_tvtitle)
        TextView teaderTvtitle;

        @BindView(R.id.teader_btn_download)
        ImageView teaderBtnDownload;



        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }



}
