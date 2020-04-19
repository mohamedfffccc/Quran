package com.example.quranonline.view.activity.adanmonth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranonline.R;
import com.example.quranonline.adapter.TimingAdapter;
import com.example.quranonline.data.model.salatlist.Datum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quranonline.data.local.Constants.SALAT;
import static com.example.quranonline.data.local.SharedPreferencesManger.setList;
import static java.util.Locale.ENGLISH;

public class AdanMonth extends AppCompatActivity {

    @BindView(R.id.prayings_rv)
    RecyclerView prayingsRv;
    LinearLayoutManager linearLayoutManager;
    TimingAdapter adapter;
    AdanMonthViewModel viewModel;
    private SimpleDateFormat simpleDateFormat;
    private String[] date_split;
    int month;
    int year;
    int method=5;
    String address="Cairo,Egypt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adan_month2);
        ButterKnife.bind(this);
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
         date_split = simpleDateFormat.format(new Date()).split("-");
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        prayingsRv.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this).get(AdanMonthViewModel.class);
        viewModel.getPrayings(address,method,date_split[1] , date_split[2]);
        viewModel.data.observe(this, new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> data) {
                adapter=new TimingAdapter(AdanMonth.this , data , AdanMonth.this);
                prayingsRv.setAdapter(adapter);

               prayingsRv.scrollToPosition(getPosition());
                setList(AdanMonth.this , SALAT , data);
            }
        });
    }
    public int getPosition()
    {
        int position;

        position = Integer.parseInt(date_split[0]);
       return  position-1;

    }

}
