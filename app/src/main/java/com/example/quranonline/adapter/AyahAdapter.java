package com.example.quranonline.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranonline.R;
import com.example.quranonline.data.model.ayat.Verse;
import com.example.quranonline.view.fragment.ayah.AyahFragment;

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
        holder.ayatexttranslation.setText(categoryList.get(position).getTranslationEn());
        holder.ayanum.setText(categoryList.get(position).getNumber()+"");

        holder.ayatafseer.setVisibility(View.GONE);

        holder.tafseermusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ayatafseer.setVisibility(View.VISIBLE);
                holder.ayatafseer.setText(fragment.getTafseerData(1, surahNumber, categoryList.get(position).getNumber()));
                Animation set = AnimationUtils.loadAnimation(context, R.animator.translation);
                holder.ayatafseer.startAnimation(set);

            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "مشاركة الاية");
                sendIntent.putExtra(Intent.EXTRA_TEXT, categoryList.get(position).getText());
                sendIntent.setType("img/plain");
                context.startActivity(Intent.createChooser(sendIntent, "اختار التطبيق الذي مشاركة النص معه :"));
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

        @BindView(R.id.ayatexttranslation)
        TextView ayatexttranslation;
        @BindView(R.id.tafseermusr)
        Button tafseermusr;
        @BindView(R.id.ayatafseer)
        TextView ayatafseer;
        @BindView(R.id.btn_share)
        ImageView btnShare;
        @BindView(R.id.ayanum)
        TextView ayanum;



        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
