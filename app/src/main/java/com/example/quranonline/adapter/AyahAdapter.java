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

import com.example.quranonline.view.fragment.ayah.AyahFragment;
import com.example.quranonline.R;
import com.example.quranonline.data.model.ayat.Verse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by medo on 13/11/2016.
 */

public class AyahAdapter extends RecyclerView.Adapter<AyahAdapter.CategoriesViewHolder> {


    int id;

    private Context context;
    //    private BaseActivity activity;
    private List<Verse> categoryList = new ArrayList<>();
    public AyahFragment fragment;
    public int surahNumber;
    //    private Typeface type;


    public AyahAdapter(Context context, List<Verse> categoryList, AyahFragment fragment, int surahNumber) {
        this.context = context;
        this.categoryList = categoryList;
        this.fragment = fragment;
        this.surahNumber = surahNumber;


    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.aya_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Verse categorydata = categoryList.get(position);
        holder.ayatext.setText(categorydata.getText());
        holder.ayatexttranslation.setVisibility(View.GONE);
        holder.ayatafseer.setVisibility(View.GONE);
        holder.translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ayatexttranslation.setVisibility(View.VISIBLE);
                holder.ayatexttranslation.setText(categoryList.get(position).getTranslationEn());
                Animation set = AnimationUtils.loadAnimation(context, R.animator.translation);
                holder.ayatexttranslation.startAnimation(set);
            }
        });
        holder.tafseermusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ayatafseer.setVisibility(View.VISIBLE);
                holder.ayatafseer.setText(fragment.getTafseerData(1,surahNumber,categoryList.get(position).getNumber()));
                Animation set = AnimationUtils.loadAnimation(context, R.animator.translation);
                holder.ayatafseer.startAnimation(set);

            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ayatext)
        TextView ayatext;
        @BindView(R.id.translate)
        Button translate;
        @BindView(R.id.ayatexttranslation)
        TextView ayatexttranslation;
        @BindView(R.id.tafseermusr)
        Button tafseermusr;
        @BindView(R.id.ayatafseer)
        TextView ayatafseer;



        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
