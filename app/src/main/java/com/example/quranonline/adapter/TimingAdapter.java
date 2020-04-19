package com.example.quranonline.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranonline.R;
import com.example.quranonline.data.model.salatlist.Datum;
import com.example.quranonline.view.activity.adanmonth.AdanMonth;
import com.example.quranonline.view.fragment.ayah.AyahFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by medo on 13/11/2016.
 */

public class TimingAdapter extends RecyclerView.Adapter<TimingAdapter.CategoriesViewHolder> {


    int id;



    private Context context;
    //    private BaseActivity activity;
    private List<Datum> categoryList = new ArrayList<>();
    public AyahFragment fragment;
    public int surahNumber;
    AdanMonth activity;
    //    private Typeface type;


    public TimingAdapter(Context context, List<Datum> categoryList , AdanMonth activity) {
        this.context = context;
        this.categoryList = categoryList;
        this.fragment = fragment;
        this.surahNumber = surahNumber;
        this.activity=activity;


    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_adhan_moquite, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.activityAdanTvDateName.setText(categoryList.get(position).getDate().getGregorian().getDate());
        holder.activityAdanTvDate.setText(getDayName(categoryList.get(position).getDate().getGregorian().getWeekday().getEn()));
        holder.fajrTv.setText("الفجر                  " + setTimings(categoryList.get(position).getTimings().getFajr()) + "            AM");
        holder.sunsetTv.setText("الشروق                " + setTimings(categoryList.get(position).getTimings().getSunrise()) + "        AM");
        holder.duhrTv.setText("الظهر                  " + setTimings(categoryList.get(position).getTimings().getDhuhr()) + "           AM");
        holder.asrTv.setText("العصر                   " + setTimings(categoryList.get(position).getTimings().getAsr()) + "             PM");
        holder.magribTv.setText("المغرب                " + setTimings(categoryList.get(position).getTimings().getMaghrib()) + "        PM");
        holder.ishaTv.setText("العشاء                  " + setTimings(categoryList.get(position).getTimings().getIsha()) + "           PM");


    }
    public String setTimings(String time)
    {
        String [] times = time.split(":");
        if (Integer.parseInt(times[0])>=12)
        {
            int p = Integer.parseInt(times [0]) - 12;
            return  p+":"+times[1];
        }
        return  time;
    }
    public  String getDayName(String name)
    {
        String day = "";
        switch (name){
            case "Saturday" :
                day="السبت";
                break;
            case "Sunday" :
                day="الاحد";
                break;
            case "Monday" :
                day="الاثنين";
                break;
            case "Tuesday" :
                day="الثلاثاء";
                break;
            case "Wednesday" :
                day="الاربعاء";
                break;
            case "Thursday" :
                day="الخميس";
                break;
            case "Friday" :
                day="الجمعة";
                break;
        }
        return day;
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activity_adan_tv_day)
        TextView activityAdanTvDay;
        @BindView(R.id.activity_adan_tv_date)
        TextView activityAdanTvDate;
        @BindView(R.id.activity_adan_tv_date_name)
        TextView activityAdanTvDateName;
        @BindView(R.id.fajr_tv)
        TextView fajrTv;
        @BindView(R.id.sunset_tv)
        TextView sunsetTv;
        @BindView(R.id.duhr_tv)
        TextView duhrTv;
        @BindView(R.id.asr_tv)
        TextView asrTv;
        @BindView(R.id.magrib_tv)
        TextView magribTv;
        @BindView(R.id.isha_tv)
        TextView ishaTv;
        @BindView(R.id.lin_salat)
        LinearLayout linSalat;
        @BindView(R.id.iv_compass)
        ImageView ivCompass;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

    }
}
