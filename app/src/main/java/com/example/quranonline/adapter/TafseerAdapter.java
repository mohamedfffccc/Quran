package com.example.quranonline.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranonline.R;
import com.example.quranonline.data.model.tafseer.Tafseer;
import com.example.quranonline.view.fragment.ayah.AyahFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by medo on 13/11/2016.
 */

public class TafseerAdapter extends RecyclerView.Adapter<TafseerAdapter.CategoriesViewHolder> {


    int id;


    private Context context;
    //    private BaseActivity activity;
    private List<Tafseer> categoryList = new ArrayList<>();
    public AyahFragment fragment;
    public int surahNumber;
    //    private Typeface type;


    public TafseerAdapter(Context context, List<Tafseer> categoryList) {
        this.context = context;
        this.categoryList = categoryList;


    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tafseer_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Tafseer categorydata = categoryList.get(position);
        holder.numberayatv.setText("الاية رقم "+categorydata.getAyahNumber() );
        holder.tafseertv.setText(categorydata.getText());

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.numberayatv)
        TextView numberayatv;
        @BindView(R.id.tafseertv)
        TextView tafseertv;


        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
